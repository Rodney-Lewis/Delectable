import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Recipe } from '../model/recipe';
import { Observable } from 'rxjs'; 

@Injectable()
export class RecipeService {

  private recipeUrl: string;
  private suffix: string;

  constructor(private http: HttpClient) { 
    this.recipeUrl = 'http://localhost:8080/recipe';
  }

  public findAll() : Observable<Recipe[]> {
    this.suffix = '/get';
    return this.http.get<Recipe[]>(this.recipeUrl+this.suffix);
  }

  public findById(id: number) : Observable<Recipe> {
    this.suffix = '/get/';
    /*
      /get/id
    */
    return this.http.get<Recipe>(this.recipeUrl+this.suffix+id.toString());
  }

  public add(recipe: Recipe) : Observable<Recipe> {
    this.suffix = '/add';
    return this.http.post<Recipe>(this.recipeUrl+this.suffix, recipe);
  }

  public update(recipe: Recipe) : Observable<Recipe> {
    this.suffix = '/update';
    return this.http.put<Recipe>(this.recipeUrl+this.suffix, recipe);
  }

  public delete(id: number) : Observable<Recipe> {
    this.suffix = '/delete';
    return this.http.delete<Recipe>(this.recipeUrl+this.suffix);
  }
}
