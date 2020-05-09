import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray } from '@angular/forms';
import { RecipeService } from '../../service/recipe.service';
import { Pantry } from '../../model/pantry';
import { PantryService } from '../../service/pantry.service';
import { FileHandlerService } from '../../service/file-handler.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  pantryItemList: Pantry[];

  recipeForm = this.formBuilder.group({
    recipe: this.formBuilder.group({
      name: [''],
      source: [''],
      prepTime: [''],
      cookTime: [''],
      imageSource: [],
      directions: this.formBuilder.array([]),
      ingredients: this.formBuilder.array([])
    }),
    image: this.formBuilder.group({
      imageMultipartFile: []
    }),
  });

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService,
    private pantryService: PantryService, private fileHandlerService: FileHandlerService, 
    private router: Router) {
  }

  ngOnInit() {
    this.pantryService.findAll().subscribe(pantryItemList => {
      this.pantryItemList = pantryItemList;
    })
    this.addDirection();
    this.addIngredient();
  }

  onFileChange(event) {
    if (event.target.files.length > 0) {
      const file = event.target.files[0];
      this.recipeForm.patchValue({
        image: { imageMultipartFile: file }
      });
    }
  }

  onSubmit() {
    const formData = new FormData();
    formData.append('imageMultipartFile', this.recipeForm.get('image.imageMultipartFile').value);
    this.fileHandlerService.add(formData).subscribe();

    this.recipeService.add(this.recipeForm.get("recipe").value).subscribe();
    this.router.navigate(['/recipe/list']);
  }

  get ingredients() {
    return this.recipeForm.get('recipe.ingredients') as FormArray;
  }

  addIngredient() {
    this.ingredients.push(this.formBuilder.group({
      pantry: [],
      quantity: [''],
      servingType: ['']
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
      instructions: ['']
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
