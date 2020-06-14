import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { Validators, FormBuilder } from '@angular/forms';
import { MealFormComponent } from 'app/delectable/meal/component/meal-form-component';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';
import { PreparedFoodService } from '../../prepared-food.service';

@Component({
  selector: 'app-prepared-food-form',
  templateUrl: './prepared-food-form.component.html',
  styleUrls: ['./prepared-food-form.component.css']
})
export class PreparedFoodFormComponent extends MealFormComponent implements OnInit {

  constructor(router: Router, formBuilder: FormBuilder, private fileHandlerService: FileHandlerService,
    private preparedFoodService: PreparedFoodService, private activatedRoute: ActivatedRoute) {
    super(router, formBuilder);
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(params => {
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
          this.preparedFoodService.update(this.getFormComponent("prepared").value, this.id).subscribe();
        } else {
          this.preparedFoodService.add(this.getFormComponent("prepared").value).subscribe();
        }
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe(() => {
          this.router.navigate(['/prepared/list']);
        });
      } else {
        if (this.edit) {
          this.preparedFoodService.update(this.getFormComponent("prepared").value, this.id).subscribe(() => {
            this.router.navigate(['/prepared/detail', this.id]);
          });
        } else {
          this.preparedFoodService.add(this.getFormComponent("prepared").value).subscribe(() => {
            this.router.navigate(['/prepared/list']);
          });
        }
      }
    }
  }

  patchFormImageValues(file: File) {
    this.form.patchValue({
      prepared: { imageSource: file.name },
      image: { imageMultipartFile: file }
    });
  }

  buildFormforEdit(id: number) {
    this.preparedFoodService.findById(id).subscribe(preparedFoodItem => {
      this.form = this.formBuilder.group({
        prepared: this.formBuilder.group({
          name: [preparedFoodItem.name, Validators.required],
          brand: [preparedFoodItem.brand, Validators.required],
          description: [preparedFoodItem.description],
          prepTimeHour: [preparedFoodItem.prepTimeHour, [Validators.min(0)]],
          prepTimeMinute: [preparedFoodItem.prepTimeMinute, [Validators.min(0), Validators.max(59)]],
          prepTimeSecond: [preparedFoodItem.prepTimeSecond, [Validators.min(0), Validators.max(59)]],
          cookTimeHour: [preparedFoodItem.cookTimeHour, [Validators.min(0)]],
          cookTimeMinute: [preparedFoodItem.cookTimeMinute, [Validators.min(0), Validators.max(59)]],
          cookTimeSecond: [preparedFoodItem.cookTimeSecond, [Validators.min(0), Validators.max(59)]],
          imageSource: [preparedFoodItem.imageSource],
          directions: this.formBuilder.array([])
        }),
        image: this.formBuilder.group({
          imageSourceFile: [''],
          imageMultipartFile: []
        }),
      });
      this.previewImage = this.fileHandlerService.getNamedImageUrl(preparedFoodItem.imageSource);
      this.populateDirections(preparedFoodItem.directions, 'prepared.directions');
    })
  }

  buildFormforCreate() {
    this.form = this.formBuilder.group({
      prepared: this.formBuilder.group({
        name: ['', Validators.required],
        brand: ['', Validators.required],
        description: [''],
        prepTimeHour: [0, [Validators.min(0)]],
        prepTimeMinute: [0, [Validators.min(0), Validators.max(59)]],
        prepTimeSecond: [0, [Validators.min(0), Validators.max(59)]],
        cookTimeHour: [0, [Validators.min(0)]],
        cookTimeMinute: [0, [Validators.min(0), Validators.max(59)]],
        cookTimeSecond: [0, [Validators.min(0), Validators.max(59)]],
        imageSource: [''],
        directions: this.formBuilder.array([])
      }),
      image: this.formBuilder.group({
        imageSourceFile: [''],
        imageMultipartFile: []
      }),
    });

    this.previewImage = this.fileHandlerService.getNamedImageUrl("");
  }

}
