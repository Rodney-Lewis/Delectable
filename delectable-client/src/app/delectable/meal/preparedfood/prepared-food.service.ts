import { Injectable } from '@angular/core';
import { PreparedFood } from './prepared-food';
import { Observable } from 'rxjs';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class PreparedFoodService {

  private preparedFoodApiEndpoint: string = "/api/prepared";

  constructor(private http: HttpClient) {
  }

  public findAll(): Observable<PreparedFood[]> {
    return this.http.get<PreparedFood[]>(this.preparedFoodApiEndpoint);
  }

  public findById(id: number): Observable<PreparedFood> {
    const endpointPattern = `${this.preparedFoodApiEndpoint}/${id}`;
    return this.http.get<PreparedFood>(endpointPattern);
  }

  public add(preparedFood: PreparedFood): Observable<PreparedFood> {
    return this.http.post<PreparedFood>(this.preparedFoodApiEndpoint, preparedFood);
  }

  public update(preparedFood: PreparedFood): Observable<PreparedFood> {
    return this.http.put<PreparedFood>(this.preparedFoodApiEndpoint, preparedFood);
  }

  public delete(id: number): Observable<PreparedFood> {
    const endpointPattern = `${this.preparedFoodApiEndpoint}/${id}`;
    return this.http.delete<PreparedFood>(endpointPattern);
  }
}
