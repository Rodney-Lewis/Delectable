import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from '../../service/recipe.service';
import { FormBuilder, FormControl, FormGroup, FormArray } from '@angular/forms';
import { PantryService } from '../../service/pantry.service';
import { Pantry } from '../../model/pantry';

@Component({
  selector: 'app-recipe-form2',
  templateUrl: './recipe-form2.component.html',
  styleUrls: ['./recipe-form2.component.css']
})
export class RecipeForm2Component implements OnInit {

  pantryItemList: Pantry[];

  recipeForm = this.formBuilder.group({
    id: [],
    name: [''],
    source: [''],
    prepTime: [''],
    cookTime: [''],
    directions: this.formBuilder.array([]),
    ingredients: this.formBuilder.array([])
  });

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService, 
    private pantryService: PantryService, private router: Router, private activatedroute:ActivatedRoute) { 
    }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      this.recipeService.findById(Number(params.get('id'))).subscribe(recipe => {      
        this.recipeForm.controls.id.setValue(recipe.id);
        this.recipeForm.controls.name.setValue(recipe.name);
        this.recipeForm.controls.source.setValue(recipe.source);
        this.recipeForm.controls.prepTime.setValue(recipe.prepTime);
        this.recipeForm.controls.cookTime.setValue(recipe.cookTime);
        this.addDirection();
        this.addIngredient();
      });
    });

    this.pantryService.findAll().subscribe(pantryItemList => {
      this.pantryItemList = pantryItemList;
    })
  }

  get ingredients() {
    return this.recipeForm.get('ingredients') as FormArray;
  }

  addIngredient() {
    this.ingredients.push(this.formBuilder.group({
        recipe: [this.recipeForm.controls.id.value],
        pantry: [],
        quantity: [''],
        servingType: ['']
    }));
  }

  removeIngredient(numberToRemove: number) {
    this.ingredients.removeAt(numberToRemove);
  }

  get directions() {
    return this.recipeForm.get('directions') as FormArray;
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
      item.patchValue({step: index + 1});
    });
  }

  onSubmit() {
    this.recipeService.update(this.recipeForm.value).subscribe()
    this.router.navigate(['/recipe/list']);
  }

}
