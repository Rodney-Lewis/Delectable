import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Restaurant } from './restaurant';

const restaurantApiURL: string = "/api/restaurant";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {


  constructor(private http: HttpClient) {
  }

  public findAll(page: number = 1, size: number = 12, query?: string): Observable<any> {
    page = page - 1;
    const params = new HttpParams().set("page", page.toString()).set("size", size.toString()).set("query", query);
    return this.http.get(restaurantApiURL, { params, responseType: 'text' });
  }

  public findById(id: number): Observable<Restaurant> {
    const endpointPattern = restaurantApiURL + `/${id}`;
    return this.http.get<Restaurant>(endpointPattern);
  }

  public add(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(restaurantApiURL, restaurant);
  }

  public update(restaurant: Restaurant, id: Number): Observable<Restaurant> {
    const endpointPattern = restaurantApiURL + `/${id}`;
    return this.http.put<Restaurant>(endpointPattern, restaurant);
  }

  public delete(id: number): Observable<Restaurant> {
    const endpointPattern = restaurantApiURL + `/${id}`;
    return this.http.delete<Restaurant>(endpointPattern);
  }
}
