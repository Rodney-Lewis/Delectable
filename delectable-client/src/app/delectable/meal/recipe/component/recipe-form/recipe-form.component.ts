import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormControl, FormGroup, Form } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
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
  editRecipe: boolean = false;
  recipeForm: FormGroup;
  previewImage: String;

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService,
    private fileHandlerService: FileHandlerService, private router: Router, private activatedroute: ActivatedRoute) {
  }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        this.editRecipe = true;
        this.recipeService.findById(Number(params.get('id'))).subscribe(data => {
          this.recipeForm = this.formBuilder.group({
            recipe: this.formBuilder.group({
              id: [data.id],
              name: [data.name, Validators.required],
              source: [data.source, Validators.required],
              prepTimeHour: [data.prepTimeHour, [Validators.min(0)]],
              prepTimeMinute: [data.prepTimeMinute, [Validators.min(0), Validators.max(59)]],
              prepTimeSecond: [data.prepTimeSecond, [Validators.min(0), Validators.max(59)]],
              cookTimeHour: [data.cookTimeHour, [Validators.min(0)]],
              cookTimeMinute: [data.cookTimeMinute, [Validators.min(0), Validators.max(59)]],
              cookTimeSecond: [data.cookTimeSecond, [Validators.min(0), Validators.max(59)]],
              imageSource: [data.imageSource],
            }),
            image: this.formBuilder.group({
              imageSourceFile: [''],
              imageMultipartFile: []
            }),
          });
          this.previewImage = this.fileHandlerService.getNamedImageUrl(data.imageSource);
        })
      } else {
        this.editRecipe = false;
        this.recipeForm = this.formBuilder.group({
          recipe: this.formBuilder.group({
            id: [0],
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
            imageSourceFile: [''],
            imageMultipartFile: []
          }),
        });
        this.previewImage = this.fileHandlerService.getNamedImageUrl("");
      }
    })
  }

  onSubmit() {
    this.formSubmitted = true;
    if (this.recipeForm.invalid) {
      return;
    } else {
      if (this.getFormComponent("image.imageSourceFile").value != "") {
        if (this.editRecipe) {
          this.recipeService.update(this.getFormComponent("recipe").value).subscribe();
        } else {
          this.recipeService.add(this.getFormComponent("recipe").value).subscribe();
        }
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe(() => {
          this.router.navigate(['/recipe/list']);
        });
      } else {
        if (this.editRecipe) {
          this.recipeService.update(this.getFormComponent("recipe").value).subscribe(() => {
            this.router.navigate(['/recipe/detail', this.getFormComponent("recipe.id").value]);
          });
        } else {
          this.recipeService.add(this.getFormComponent("recipe").value).subscribe(() => {
            this.router.navigate(['/recipe/list']);
          });
        }
      }
    }
  }

  getFormComponent(component: string) {
    return this.recipeForm.get(component);
  }

  getFormComponentAsFormArray(component: string) {
    return this.recipeForm.get(component) as FormGroup;
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

  triggerFileUpdload() {
    document.getElementById("thumbnail").click();
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      if (event.target.files[0].type == "image/jpeg" || event.target.files[0].type == "image/png") {
        this.invalidImageType = false;
        if (event.target.files[0].size < 10000000) {
          this.invalidImageSize = false;
          const file = event.target.files[0];
          this.recipeForm.patchValue({
            recipe: { imageSource: file.name },
            image: { imageMultipartFile: file }
          });
          this.previewFile(file);
        }
        else {
          this.invalidImageSize = true;
        }
      } else {
        this.invalidImageType = true;
      }
    }
  }

  previewFile(file: File) {
    const reader = new FileReader();
    reader.onload = (e: any) => {
      this.previewImage = e.target.result;
    };
    reader.readAsDataURL(file);
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
