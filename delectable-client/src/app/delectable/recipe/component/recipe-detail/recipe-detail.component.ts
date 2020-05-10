import { Component, OnInit } from '@angular/core';
import { Recipe } from '../../recipe';
import { RecipeService } from '../../recipe.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe-detail',
  templateUrl: './recipe-detail.component.html',
  styleUrls: ['./recipe-detail.component.css']
})
export class RecipeDetailComponent implements OnInit {

  recipe:Recipe = new Recipe();
  id:number;
  
  constructor(private recipeService:RecipeService, private activatedroute:ActivatedRoute) { }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {  
      this.recipeService.findById(Number(params.get('id'))).subscribe(data => {
        this.recipe = data;
      })
    })
  }
}
