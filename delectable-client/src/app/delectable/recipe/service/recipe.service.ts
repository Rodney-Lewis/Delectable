import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Recipe } from '../model/recipe';
import { Observable } from 'rxjs';

@Injectable()
export class RecipeService {

  private recipeApiEndpoint: string = "/api/recipe";

  constructor(private http: HttpClient) {
  }

  public findAll(page: number = 1, size: number = 12, query?: string): Observable<any> {
    page = page - 1;
    const params = new HttpParams().set("page", page.toString()).set("size", size.toString()).set("query", query);
    return this.http.get(this.recipeApiEndpoint, { params, observe: 'response'});      
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
