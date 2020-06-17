import { Injectable } from '@angular/core';
import { Restaurant } from './restaurant';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RestaurantService {

  private restaurantApiEndpoint: string = "/api/restaurant";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<Restaurant[]> {
    return this.http.get<Restaurant[]>(this.restaurantApiEndpoint);
  }

  public findById(id: number): Observable<Restaurant> {
    const endpointPattern = `${this.restaurantApiEndpoint}/${id}`;
    return this.http.get<Restaurant>(endpointPattern);
  }

  public add(restaurant: Restaurant): Observable<Restaurant> {
    return this.http.post<Restaurant>(this.restaurantApiEndpoint, restaurant);
  }

  public update(restaurant: Restaurant, id: Number): Observable<Restaurant> {
    const endpointPattern = `${this.restaurantApiEndpoint}/${id}`;
    return this.http.put<Restaurant>(endpointPattern, restaurant);
  }

  public delete(id: number): Observable<Restaurant> {
    const endpointPattern = `${this.restaurantApiEndpoint}/${id}`;
    return this.http.delete<Restaurant>(endpointPattern);
  }
}
