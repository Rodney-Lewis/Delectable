import { Component, OnInit } from '@angular/core';
import { Recipe } from 'app/delectable/recipe/recipe';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  recipe: Recipe = new Recipe();
  totalTimeHour: number = 0;
  totalTimeMinute: number = 0;
  totalTimeSecond: number = 0;

  constructor(private recipeService: RecipeService, private fileHandlerService: FileHandlerService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      this.recipeService.findById(Number(params.get('id'))).subscribe(data => {
        this.recipe = data;
        this.recipe.imageSource = this.fileHandlerService.getNamedImageUrl(this.recipe.imageSource);
        if (this.recipe.cookTimeSecond + this.recipe.prepTimeSecond > 60) {
          this.totalTimeMinute++;
          this.totalTimeSecond = ((this.recipe.cookTimeSecond + this.recipe.prepTimeSecond) % 60);
        } else {
          this.totalTimeSecond = (this.recipe.cookTimeSecond + this.recipe.prepTimeSecond);
        }
        if (this.recipe.cookTimeMinute + this.recipe.prepTimeMinute > 60) {
          this.totalTimeHour++;
          this.totalTimeMinute = this.totalTimeMinute + ((this.recipe.cookTimeMinute + this.recipe.prepTimeMinute) % 60);
        } else {
          this.totalTimeMinute = (this.recipe.cookTimeMinute + this.recipe.prepTimeMinute);
        }
        this.totalTimeHour = this.totalTimeHour + this.recipe.cookTimeHour + this.recipe.prepTimeHour;
      })
    })
  }

  delete(id) {
    this.recipeService.delete(id).subscribe(data => {
      this.router.navigate(['/recipe/list']);
    })
  }

  displayCookTimeHour() {
    return this.recipe.cookTimeHour > 0;
  }

  displayCookTimeMinute() {
    return this.recipe.cookTimeMinute > 0;
  }

  displayCookTimeSecond() {
    return this.recipe.cookTimeSecond > 0;
  }

  displayPrepTimeHour() {
    return this.recipe.prepTimeHour > 0;
  }

  displayPrepTimeMinute() {
    return this.recipe.prepTimeMinute > 0;
  }

  displayPrepTimeSecond() {
    return this.recipe.prepTimeSecond > 0;
  }

  displayDeleteButton() {
    return !this.recipe.deleted;
  }

}