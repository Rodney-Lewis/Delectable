import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pantry } from './pantry';

@Injectable()
export class PantryService {

  private pantryApiEndpoint: string = "/api/pantry";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Pantry[]> {
    return this.http.get<Pantry[]>(this.pantryApiEndpoint);
  }

  public findById(id: number): Observable<Pantry> {
    const endpointPattern = `${this.pantryApiEndpoint}/${id}`;
    return this.http.get<Pantry>(endpointPattern);
  }

  public add(pantry: Pantry): Observable<Pantry> {
    return this.http.post<Pantry>(this.pantryApiEndpoint, pantry);
  }

  public update(pantry: Pantry): Observable<Pantry> {
    return this.http.put<Pantry>(this.pantryApiEndpoint, pantry);
  }

  public delete(id: number): Observable<Pantry> {
    const endpointPattern = `${this.pantryApiEndpoint}/${id}`;
    return this.http.delete<Pantry>(endpointPattern);
  }
}
