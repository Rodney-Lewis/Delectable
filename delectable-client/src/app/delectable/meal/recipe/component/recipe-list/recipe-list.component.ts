import { Component, OnInit } from '@angular/core';

import { FileHandlerService } from 'app/delectable//imagehandler/file-handler.service';
import { Recipe } from '../../recipe';
import { RecipeService } from '../../recipe.service';

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
        recipe.imageSource = this.fileHandlerService.getNamedImageUrl(recipe.imageSource);
      }
    })
  }
}
