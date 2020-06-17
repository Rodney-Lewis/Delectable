import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class IngredientService {

  private ingredientApiEndpoint: string = "/api/ingredient";

  constructor(private http: HttpClient) {
  }

  public getUnits(): Observable<string[]> {
    const endpointPattern = `${this.ingredientApiEndpoint}/measurements`
    return this.http.get<string[]>(endpointPattern);
  }
}

