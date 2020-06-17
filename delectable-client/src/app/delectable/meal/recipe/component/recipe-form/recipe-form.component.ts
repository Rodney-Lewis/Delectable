import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormControl, FormGroup, Form } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';
import { RecipeService } from '../../recipe.service';
import { IngredientService } from 'app/delectable/ingredient/ingredient.service';
import { Ingredient } from 'app/delectable/ingredient/ingredient';
import { Direction } from 'app/delectable/meal/instruction';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  invalidImageType: boolean;
  invalidImageSize: boolean;
  formSubmitted: boolean;
  editRecipe: boolean;
  recipeForm: FormGroup;
  previewImage: String;
  units: String[];
  id: number;

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService, private ingredientService: IngredientService,
    private fileHandlerService: FileHandlerService, private router: Router, private activatedroute: ActivatedRoute) {
  }

  ngOnInit() {
    this.invalidImageSize = false;
    this.invalidImageType = false;
    this.formSubmitted = false;
    this.editRecipe = false;

    this.activatedroute.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        this.editRecipe = true;
        this.id = Number.parseInt(params.get('id'));
        this.recipeService.findById(Number(params.get('id'))).subscribe(data => {
          this.recipeForm = this.formBuilder.group({
            recipe: this.formBuilder.group({
              name: [data.name, Validators.required],
              source: [data.source, Validators.required],
              prepTimeHour: [data.prepTimeHour, [Validators.min(0)]],
              prepTimeMinute: [data.prepTimeMinute, [Validators.min(0), Validators.max(59)]],
              prepTimeSecond: [data.prepTimeSecond, [Validators.min(0), Validators.max(59)]],
              cookTimeHour: [data.cookTimeHour, [Validators.min(0)]],
              cookTimeMinute: [data.cookTimeMinute, [Validators.min(0), Validators.max(59)]],
              cookTimeSecond: [data.cookTimeSecond, [Validators.min(0), Validators.max(59)]],
              imageSource: [data.imageSource],
              description: [data.description],
              ingredients: this.formBuilder.array([]),
              directions: this.formBuilder.array([])
            }),
            image: this.formBuilder.group({
              imageSourceFile: [''],
              imageMultipartFile: []
            }),
          });
          this.previewImage = this.fileHandlerService.getNamedImageUrl(data.imageSource);
          this.populateDirections(data.directions);
          this.ingredientService.getUnits().subscribe(units => {
            this.units = units;
            this.populateIngredients(data.ingredients);
          })
        })
      } else {
        this.editRecipe = false;
        this.recipeForm = this.formBuilder.group({
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
            description: [''],
            ingredients: this.formBuilder.array([]),
            directions: this.formBuilder.array([])
          }),
          image: this.formBuilder.group({
            imageSourceFile: [''],
            imageMultipartFile: []
          }),
        });
        this.previewImage = this.fileHandlerService.getNamedImageUrl("");
        this.addDirection();
        this.ingredientService.getUnits().subscribe(data => {
          this.units = data;
          this.addIngredient();
        })
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
          this.recipeService.update(this.getFormComponent("recipe").value, this.id).subscribe();
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
          this.recipeService.update(this.getFormComponent("recipe").value, this.id).subscribe(() => {
            this.router.navigate(['/recipe/detail', this.id]);
          });
        } else {
          this.recipeService.add(this.getFormComponent("recipe").value).subscribe(() => {
            this.router.navigate(['/recipe/list']);
          });
        }
      }
    }
  }

  //File handler
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

  //Form manipulation and validation
  getFormComponent(component: string) {
    return this.recipeForm.get(component);
  }

  getFormArrayComponent(component: string) {
    return this.recipeForm.get(component) as FormArray;
  }

  formFieldInvalid(component: string) {
    return this.getFormComponent(component).invalid && (this.getFormComponent(component).dirty || this.getFormComponent(component).touched || this.formSubmitted)
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

  populateIngredients(ingredients: Ingredient[]) {
    for (let ingredient of ingredients) {
      this.getFormArrayComponent('recipe.ingredients').push(this.formBuilder.group({
        name: [ingredient.name, Validators.required],
        measurement: [ingredient.measurement, [Validators.required, Validators.maxLength(8), Validators.minLength(1)]],
        unit: [ingredient.unit, Validators.required]
      }));
    }
  }
  
  addIngredient() {
    this.getFormArrayComponent('recipe.ingredients').push(this.formBuilder.group({
      name: ['', Validators.required],
      measurement: ['', [Validators.required, Validators.maxLength(8), Validators.minLength(1)]],
      unit: [this.units[0], Validators.required]
    }));
  }

  removeIngredient(numberToRemove: number) {
    this.getFormArrayComponent('recipe.ingredients').removeAt(numberToRemove);
  }

  populateDirections(directions: Direction[]) {
    for (let direction of directions) {
      this.getFormArrayComponent('recipe.directions').push(this.formBuilder.group({
        step: [direction.step],
        instruction: [direction.instruction, Validators.required]
      }));
    }
  }

  addDirection() {
    this.getFormArrayComponent('recipe.directions').push(this.formBuilder.group({
      step: [this.getFormArrayComponent('recipe.directions').length + 1],
      instruction: ['', Validators.required]
    }));
  }

  removeDirection(numberToRemove: number) {
    this.getFormArrayComponent('recipe.directions').removeAt(numberToRemove);
    this.reorderSteps();
  }

  reorderSteps() {
    this.getFormArrayComponent('recipe.directions').controls.forEach((item, index) => {
      item.patchValue({ step: index + 1 });
    });
  }
}
