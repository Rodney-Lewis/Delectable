import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PantryItem } from '../model/pantry';

@Injectable()
export class PantryService {

  private pantryApiEndpoint: string = "/api/pantry";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<PantryItem[]> {
    return this.http.get<PantryItem[]>(this.pantryApiEndpoint);
  }

  public findById(id: number): Observable<PantryItem> {
    const endpointPattern = `${this.pantryApiEndpoint}/${id}`;
    return this.http.get<PantryItem>(endpointPattern);
  }

  public add(pantry: PantryItem): Observable<PantryItem> {
    return this.http.post<PantryItem>(this.pantryApiEndpoint, pantry);
  }

  public update(pantry: PantryItem, id: number): Observable<PantryItem> {
    const endpointPattern = `${this.pantryApiEndpoint}/${id}`;
    return this.http.put<PantryItem>(this.pantryApiEndpoint, pantry);
  }

  public delete(id: number): Observable<PantryItem> {
    const endpointPattern = `${this.pantryApiEndpoint}/${id}`;
    return this.http.delete<PantryItem>(endpointPattern);
  }
}
