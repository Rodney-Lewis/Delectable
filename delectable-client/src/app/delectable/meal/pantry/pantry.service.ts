import { Injectable } from '@angular/core';
import { HttpClient, HttpParams, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PantryItem } from './pantry';

@Injectable()
export class PantryService {

  private pantryApiEndpoint: string = "/api/pantry";

  constructor(private http: HttpClient) {
  }

  public findAll(page: number = 1, size: number = 12, query?: string): Observable<HttpResponse<any>> {
    page = page - 1;
    const params = new HttpParams().set("page", page.toString()).set("size", size.toString()).set("query", query);
    return this.http.get(this.pantryApiEndpoint, {params, responseType: 'text', observe: 'response'});
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
