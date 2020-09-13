import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Recipe } from '../model/recipe';
import { Observable } from 'rxjs';

@Injectable()
export class RecipeService {

  private recipeApiEndpoint: string = "/api/recipe";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(this.recipeApiEndpoint);
  }

  public findById(id: number): Observable<Recipe> {
    const endpointPattern = `${this.recipeApiEndpoint}/${id}`;
    return this.http.get<Recipe>(endpointPattern);
  }

  public add(recipe: Recipe): Observable<Recipe> {
    return this.http.post<Recipe>(this.recipeApiEndpoint, recipe);
  }

  public update(recipe: Recipe, id: number): Observable<Recipe> {
    const endpointPattern = `${this.recipeApiEndpoint}/${id}`;
    return this.http.put<Recipe>(endpointPattern, recipe);
  }

  public delete(id: number): Observable<Recipe> {
    const endpointPattern = `${this.recipeApiEndpoint}/${id}`;
    return this.http.delete<Recipe>(endpointPattern);
  }
}
