import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private endpoint: string = "/api/account";

  constructor(private http: HttpClient) {
  }
  
  public getCurrentUserDetails() : Observable<any> {
    const endpointPattern = `${this.endpoint}`;
    return this.http.get(endpointPattern);
  }

  public updateCurrentUserDetails(user: User) : Observable<any> {
    const endpointPattern = `${this.endpoint}/details`;
    return this.http.put<User>(endpointPattern, user) 
  }

  public updateCurrentUserPassword(formData: any) : Observable<any> {
    const endpointPattern = `${this.endpoint}/credentials`;
    return this.http.put<User>(endpointPattern, formData) 
  }

}
