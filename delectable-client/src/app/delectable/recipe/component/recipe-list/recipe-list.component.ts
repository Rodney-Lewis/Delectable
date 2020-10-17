import { Component, OnInit } from '@angular/core';
import { RecipeService } from '../../service/recipe.service';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  constructor(private recipeService: RecipeService, private fileHandlerService: FileHandlerService, private activatedRoute: ActivatedRoute) { }

  responseBody: any;

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.recipeService.findAll(params.cp, params.ps, params.s).subscribe(responseFull => {
        this.responseBody = responseFull.body;
        this.responseBody.content.forEach(recipe => {
          recipe.imageSource = this.fileHandlerService.getNamedImageUrl(recipe.imageSource);
        });
      },
      );
    })
  }
}
