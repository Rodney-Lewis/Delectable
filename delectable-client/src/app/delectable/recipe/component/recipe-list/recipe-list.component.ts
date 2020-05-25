import { Component, OnInit } from '@angular/core';
import { Recipe } from 'app/delectable/recipe/recipe';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { FileHandlerService } from 'app/delectable//imagehandler/file-handler.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  recipes: Recipe[];
  constructor(private recipeService: RecipeService, private fileHandlerService: FileHandlerService) { }

  ngOnInit() {
    this.recipeService.findAll().subscribe(data => {
      this.recipes = data;
      for (let recipe of this.recipes) {
        recipe.imageSource = this.fileHandlerService.buildImageUrl(recipe.imageSource);
      }
    })
  }
}
