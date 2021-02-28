import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private endpoint: string = "/api/user";

  constructor(private http: HttpClient) {
  }

  public findAll(page: number = 1, size: number = 10): Observable<any> {
    page = page - 1;
    const params = new HttpParams().set("page", page.toString()).set("size", size.toString());
    return this.http.get(this.endpoint + "/all", { params, observe: 'response' });
  }

  public getRoles() : Observable<any> {
    return this.http.get(this.endpoint + "/groups");
  }

 
  public getById(id: number) : Observable<User> {
    const endpointPattern = `${this.endpoint}/${id}`;
    return this.http.get<User>(endpointPattern);
  }

}
