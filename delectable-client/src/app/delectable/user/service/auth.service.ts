import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TokenStorageService } from './token-storage.service';
import { ActivatedRouteSnapshot } from '@angular/router';
import { Role } from '../model/Role';

const AUTH_API = '/api/auth/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  redirectUrl: string;

  constructor(private http: HttpClient, private tokenStorageService: TokenStorageService) { }

  isLoggedIn(): boolean {
    if (this.tokenStorageService.getToken()) {
      let expTime = new Date(this.tokenStorageService.getExpiration()).getTime();
      let currentTime = Date.now();
      if (expTime > currentTime) {
        return true;
      } else {
        this.tokenStorageService.clearSession();
        return false;
      }
    } else {
      return false;
    }
  }

  hasPermissions(role: any): boolean {
    if (role && this.isLoggedIn()) {
      const userRoles = this.tokenStorageService.getRoles();
      var requiredRole = Role[role];
      var userRole = Role[userRoles[0]];
      if (userRole <= requiredRole) {
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'signin', {
      username: credentials.username,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API + 'signup', {
      username: user.username,
      email: user.email,
      password: user.password
    }, httpOptions);
  }
}