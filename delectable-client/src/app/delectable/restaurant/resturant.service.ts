import { Injectable } from '@angular/core';
import { Restuarant } from './restuarant';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ResturantService {

  private resturantApiEndpoint: string = "/api/resturant";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Restuarant[]> {
    return this.http.get<Restuarant[]>(this.resturantApiEndpoint);
  }

  public findById(id: number): Observable<Restuarant> {
    const endpointPattern = `${this.resturantApiEndpoint}/${id}`;
    return this.http.get<Restuarant>(endpointPattern);
  }

  public add(resturant: Restuarant): Observable<Restuarant> {
    return this.http.post<Restuarant>(this.resturantApiEndpoint, resturant);
  }

  public update(resturant: Restuarant): Observable<Restuarant> {
    return this.http.put<Restuarant>(this.resturantApiEndpoint, resturant);
  }

  public delete(id: number): Observable<Restuarant> {
    const endpointPattern = `${this.resturantApiEndpoint}/${id}`;
    return this.http.delete<Restuarant>(endpointPattern);
  }
}
