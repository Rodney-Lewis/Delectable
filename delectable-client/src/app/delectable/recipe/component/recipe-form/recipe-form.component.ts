import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  invalidImageType: boolean = false;
  invalidImageSize: boolean = false;
  formSubmitted: boolean = false;

  recipeForm = this.formBuilder.group({
    recipe: this.formBuilder.group({
      name: ['', Validators.required],
      source: ['', Validators.required],
      prepTimeHour: [0, [Validators.min(0)]],
      prepTimeMinute: [0, [Validators.min(0), Validators.max(59)]],
      prepTimeSecond: [0, [Validators.min(0), Validators.max(59)]],
      cookTimeHour: [0, [Validators.min(0)]],
      cookTimeMinute: [0, [Validators.min(0), Validators.max(59)]],
      cookTimeSecond: [0, [Validators.min(0), Validators.max(59)]],
      imageSource: [''],
    }),
    image: this.formBuilder.group({
      imageMultipartFile: []
    }),
  });

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService,
    private fileHandlerService: FileHandlerService, private router: Router) {
  }

  ngOnInit() { }

  onSubmit() {
    this.formSubmitted = true;
    if (this.recipeForm.invalid) {
      return;
    } else {
      if (this.getFormComponent("recipe.imageSource").value != "") {
        this.recipeService.add(this.getFormComponent("recipe").value).subscribe();
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe(data => {
          this.router.navigate(['/recipe/list']);
        });
      } else {
        this.recipeService.add(this.getFormComponent("recipe").value).subscribe(data => {
          this.router.navigate(['/recipe/list']);
        });
      }
    }
  }

  getFormComponent(component: string) {
    return this.recipeForm.get(component);
  }

  formFieldInvalid(component: string) {
    return this.getFormComponent(component).invalid && (this.getFormComponent(component).dirty || this.getFormComponent(component).touched)
  }

  formFieldRequired(component: string) {
    if (this.formFieldInvalid(component))
      return this.getFormComponent(component).errors?.required;
  }

  formFieldMin(component: string) {
    if (this.formFieldInvalid(component))
      return this.getFormComponent(component).errors?.min;
  }

  formFieldMax(component: string) {
    if (this.formFieldInvalid(component))
      return this.getFormComponent(component).errors?.max;
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      if (event.target.files[0].type == "image/jpeg" || event.target.files[0].type == "image/png") {
        this.invalidImageType = false;
        if (event.target.files[0].size < 10000000) {
          this.invalidImageSize = false;
          const file = event.target.files[0];
          this.recipeForm.patchValue({
            image: { imageMultipartFile: file }
          });
        }
        else {
          this.invalidImageSize = true;
        }
      } else {
        this.invalidImageType = true;
      }
    }
  }

  get ingredients() {
    return this.recipeForm.get('recipe.ingredients') as FormArray;
  }

  addIngredient() {
    this.ingredients.push(this.formBuilder.group({
      pantry: ['', Validators.required],
      quantity: ['', [Validators.required, Validators.maxLength(8), Validators.minLength(1)]],
      servingType: ['', Validators.required]
    }));
  }

  removeIngredient(numberToRemove: number) {
    this.ingredients.removeAt(numberToRemove);
  }

  get directions() {
    return this.recipeForm.get('recipe.directions') as FormArray;
  }

  addDirection() {
    this.directions.push(this.formBuilder.group({
      step: [this.directions.length + 1],
      instructions: ['', Validators.required]
    }));
  }

  removeDirection(numberToRemove: number) {
    this.directions.removeAt(numberToRemove);
    this.reorderSteps();
  }

  reorderSteps() {
    this.directions.controls.forEach((item, index) => {
      item.patchValue({ step: index + 1 });
    });
  }
}
