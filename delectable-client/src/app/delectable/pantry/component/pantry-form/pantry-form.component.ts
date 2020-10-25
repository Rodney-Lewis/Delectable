import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';
import { PantryItem } from 'app/delectable/pantry/model/pantry';
import { Router, ActivatedRoute } from '@angular/router';
import { FormWithImageComponent } from 'app/delectable/_component/form-with-image-component';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';

@Component({
  selector: 'app-pantry-form',
  templateUrl: './pantry-form.component.html',
  styleUrls: ['./pantry-form.component.css']
})
export class PantryFormComponent extends FormWithImageComponent implements OnInit {

  constructor(formBuilder: FormBuilder, private pantryService: PantryService,
    router: Router, private activatedRoute: ActivatedRoute, private fileHandlerService: FileHandlerService) {
    super(router, formBuilder);
    console.log(this.form);
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
    })
  }

  buildFormforCreate() {
    let formGroup = this.getFormGroupComponent('element');
    formGroup.addControl('name', new FormControl('', Validators.required));
    formGroup.addControl('directions', new FormArray([]));
    formGroup.addControl('imageSource', new FormControl(''));
  }

}
