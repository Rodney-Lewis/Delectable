import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/shared/service/file-handler.service';
import { RecipeService } from '../../service/recipe.service';

@Component({
  selector: 'app-recipe-list',
  templateUrl: './recipe-list.component.html',
  styleUrls: ['./recipe-list.component.css']
})
export class RecipeListComponent implements OnInit {

  constructor(private recipeService: RecipeService, private fileHandlerService: FileHandlerService, private activatedRoute: ActivatedRoute) { }

  responseBody: any;
  pageSize: number = 12;

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {

      if(params.ps)
        this.pageSize = params.ps;

      this.recipeService.getPage(params.cp, this.pageSize, params.s).subscribe(responseFull => {
        this.responseBody = responseFull.body;
        this.responseBody.content.forEach(recipe => {
          recipe.imageSource = this.fileHandlerService.getNamedImageUrl(recipe.imageSource);
        });
      },
      );
    })
  }
}
