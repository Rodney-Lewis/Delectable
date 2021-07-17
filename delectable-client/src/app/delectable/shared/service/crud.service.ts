import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export abstract class CRUDService {

  APIEndpoint: string = "";

  constructor(protected http: HttpClient, APIEndpoint: string) {
    this.APIEndpoint = APIEndpoint;
  }

  public create(payload: any): Observable<any> {
    return this.http.post<any>(this.APIEndpoint, payload);
  }

  public getById(id: number): Observable<any> {
    const endpointPattern = this.APIEndpoint + `/${id}`;
    return this.http.get<any>(endpointPattern);
  }

  public getPage(page: number = 1, size: number = 12, name: string = ""): Observable<any> {
    page = page - 1;
    const params = new HttpParams().set("page", page.toString()).set("size", size.toString()).set("name", name).set("sort", "name").set("name.dir", "ASC");
    return this.http.get(this.APIEndpoint, { params, observe: 'response' });
  }

  public update(payload: any, id: Number): Observable<any> {
    const endpointPattern = this.APIEndpoint + `/${id}`;
    return this.http.put<any>(endpointPattern, payload);
  }

  public delete(id: number): Observable<any> {
    const endpointPattern = this.APIEndpoint + `/${id}`;
    return this.http.delete<any>(endpointPattern);
  }

}
