import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormArray } from '@angular/forms';
import { RecipeService } from '../../service/recipe.service';
import { Pantry } from '../../model/pantry';
import { PantryService } from '../../service/pantry.service';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  recipeForm: FormGroup;
  pantryItems: Pantry[];

  maxHours:number;
  maxMinutes:number;
  maxSeconds:number;
  maxHourRange:number[];
  maxMinuteRange:number[];
  maxSecondRange:number[];
  payload:string;

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService, private pantryService: PantryService) { 
    this.maxHours = 24;
    this.maxMinutes = 59;
    this.maxSeconds = 59;
    this.maxHourRange = [];
    this.maxMinuteRange = [];
    this.maxSecondRange = [];
  }

  ngOnInit() {

    for(var index = 0; index <= this.maxHours; index++) {
      this.maxHourRange.push(index);
    }

    for(var index = 0; index <= this.maxMinutes; index++) {
      this.maxMinuteRange.push(index);
    }

    for(var index = 0; index <= this.maxSeconds; index++) {
      this.maxSecondRange.push(index);
    }

    this.pantryService.findAll().subscribe(data => {
      this.pantryItems = data;
      console.log(data);
    });

    this.recipeForm = this.formBuilder.group({
      name: new FormControl(''),
      prepTime: new FormControl(''),
      cookTime: new FormControl(''),
      source: new FormControl(''),
      directions: new FormArray ([ 
        new FormGroup({
          step: new FormControl(1),
          instructions: new FormControl('')
        })
      ]),
      ingredients: new FormArray([
        new FormGroup({
          pantryId: new FormControl(),
          quantity: new FormControl(''),
          servingType: new FormControl('')
        })
      ])
    });
  }

  get getIngredientsFormControls() {return this.recipeForm.controls.ingredients as FormArray; }
  get getDirectionsFormControls() {return this.recipeForm.controls.directions as FormArray; }

  addIngredient() {
    this.getIngredientsFormControls.push(this.formBuilder.group({
        quantity: new FormControl(''),
        servingType: new FormControl('')
    }));
  }

  removeIngredient(numberToRemove: number) {
    this.getIngredientsFormControls.removeAt(numberToRemove);
  }

  addDirection() {
    this.getDirectionsFormControls.push(this.formBuilder.group({
        step: new FormControl(this.getDirectionsFormControls.length + 1),
        instructions: new FormControl('')
    }));
  }

  removeDirection(numberToRemove: number) {
    this.getDirectionsFormControls.controls.splice(numberToRemove, 1);
    /*
    for(let i in this.getDirectionsFormControls.controls) {
      this.getDirectionsFormControls.controls[i].patchValue(i + 1, "step");
      console.log(this.getDirectionsFormControls.controls[i]);
      console.log(i);
    }
    */
  }

  onSubmit() {
    this.pantryService.add(this.recipeForm.value).subscribe();
  }

}
