import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs'; 
import { Pantry } from '../model/pantry';

@Injectable()
export class PantryService {

  private pantryUrl: string;
  private suffix: string;

  constructor(private http: HttpClient) { 
    this.pantryUrl = 'http://localhost:8080/pantry';
  }

  public findAll() : Observable<Pantry[]> {
    this.suffix = '/get';
    return this.http.get<Pantry[]>(this.pantryUrl+this.suffix);
  }

  public findById(id: number) : Observable<Pantry> {
    this.suffix = `/get/${id}`;
    return this.http.get<Pantry>(this.pantryUrl+this.suffix);
  }

  public add(pantry: Pantry) : Observable<Pantry> {
    this.suffix = '/add';
    return this.http.post<Pantry>(this.pantryUrl+this.suffix, pantry);
  }

  public update(pantry: Pantry) : Observable<Pantry> {
    this.suffix = '/update';
    return this.http.put<Pantry>(this.pantryUrl+this.suffix, pantry);
  }

  public delete(id: number) : Observable<Pantry> {
    this.suffix = '/delete';
    return this.http.delete<Pantry>(this.pantryUrl+this.suffix);
  }
}
