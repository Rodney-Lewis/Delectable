import { ChangeDetectorRef, Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { PantryService } from '../../pantry.service';
import { MealForm } from 'app/delectable/meal/_shared/meal-form';

@Component({
  selector: 'app-pantry-form',
  templateUrl: './pantry-form.component.html',
  styleUrls: ['./pantry-form.component.css']
})
export class PantryFormComponent extends MealForm implements OnInit {

  constructor(formBuilder: FormBuilder, router: Router, change: ChangeDetectorRef, private pantryService: PantryService,
    private activatedRoute: ActivatedRoute, private fileHandlerService: FileHandlerService) {
    super(router, formBuilder, change);
  }

  ngOnInit() {
    this.activatedRoute.queryParamMap.subscribe(params => {
      if (params.get('id') == null) {
        this.buildFormforCreate();
        this.edit = false;
      } else {
        this.id = Number.parseInt(params.get('id'));
        this.buildFormforEdit(this.id);
        this.edit = true;
      }
    })
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.form.invalid) {
      return;
    } else {
      if (this.getFormComponent("image.imageSourceFile").value != "") {
        if (this.edit) {
          this.pantryService.update(this.getFormComponent('element').value, this.id).subscribe();
        }
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe(() => {
          this.router.navigate(['/pantry/list']);
        })
      } else {
        if (this.edit) {
          this.pantryService.update(this.getFormComponent("element").value, this.id).subscribe(() => {
            this.router.navigate(['/pantry/detail', this.id]);
          })
        }
      }
      this.pantryService.add(this.getFormComponent('element').value).subscribe(() => {
        this.router.navigate(['/pantry/list']);

      })
    }
  }

  buildFormforEdit(id: number) {
    this.pantryService.findById(id).subscribe(pantryItem => {
      let formGroup = this.getFormGroupComponent('element');
      formGroup.addControl('name', new FormControl(pantryItem.name, Validators.required));
      formGroup.addControl('directions', new FormControl(pantryItem.directions))
      formGroup.addControl('imageSource', new FormControl(pantryItem.imageSource));
      formGroup.addControl('prepTimeHour', new FormControl(pantryItem.prepTimeHour, [Validators.min(0), Validators.max(72)]));
      formGroup.addControl('prepTimeMinute', new FormControl(pantryItem.prepTimeMinute, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('prepTimeSecond', new FormControl(pantryItem.prepTimeSecond, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('cookTimeHour', new FormControl(pantryItem.cookTimeHour, [Validators.min(0), Validators.max(72)]));
      formGroup.addControl('cookTimeMinute', new FormControl(pantryItem.cookTimeMinute, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('cookTimeSecond', new FormControl(pantryItem.cookTimeSecond, [Validators.min(0), Validators.max(59)]));
      formGroup.addControl('description', new FormControl(pantryItem.description));
      formGroup.addControl('servings', new FormControl(pantryItem.servings, [Validators.min(0), Validators.max(99)]));
    })
  }

  buildFormforCreate() {
    let formGroup = this.getFormGroupComponent('element');
    formGroup.addControl('name', new FormControl('', Validators.required));
    formGroup.addControl('directions', new FormArray([]));
    formGroup.addControl('imageSource', new FormControl(''));
    formGroup.addControl('prepTimeHour', new FormControl(0, [Validators.min(0), Validators.max(72)]));
    formGroup.addControl('prepTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('prepTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('cookTimeHour', new FormControl(0, [Validators.min(0), Validators.max(72)]));
    formGroup.addControl('cookTimeMinute', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('cookTimeSecond', new FormControl(0, [Validators.min(0), Validators.max(59)]));
    formGroup.addControl('description', new FormControl(''));
    formGroup.addControl('servings', new FormControl(1, [Validators.min(0), Validators.max(99)]));
  }

}
