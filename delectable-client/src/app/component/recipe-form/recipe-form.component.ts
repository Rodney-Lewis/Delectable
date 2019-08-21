import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { RecipeService } from '../../service/recipe.service';
import { Pantry } from '../../model/pantry';
import { PantryService } from '../../service/pantry.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  pantryItems: Pantry[];
  recipeForm = this.formBuilder.group({
    name: [''],
    source: [''],
    prepTime: [''],
    cookTime: [''],
  });

  constructor(private formBuilder: FormBuilder, private recipeService: RecipeService, 
    private pantryService: PantryService, private router: Router) {
  }

  ngOnInit() {
    this.pantryService.findAll().subscribe(data => {
      this.pantryItems = data;
    });
  }

  onSubmit() {
    this.recipeService.add(this.recipeForm.value).subscribe(recipe => {
      this.router.navigate(['/recipe/add/2', recipe.id]);
    });
  }

}
