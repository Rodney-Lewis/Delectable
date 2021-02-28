import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CRUDService } from 'app/delectable/shared/service/crud.service';

const recipeAPIEndpoint: string = "/api/recipe";

@Injectable({
  providedIn: 'root'
})
export class RecipeService extends CRUDService {

  constructor(http: HttpClient) {
    super(http, recipeAPIEndpoint)
  }

}
