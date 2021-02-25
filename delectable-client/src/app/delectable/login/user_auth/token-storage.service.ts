import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

const TOKEN_KEY = 'auth-token';
const USER_KEY = 'auth-user';

@Injectable({
  providedIn: 'root'
})
export class TokenStorageService {

  constructor(private router: Router) { }

  clearSession(): void {
    window.sessionStorage.clear();
    this.router.navigate(['/']);
    setTimeout(() => {
      window.location.reload();
    }, 5);
  }

  public saveToken(token: string): void {
    window.sessionStorage.removeItem(TOKEN_KEY);
    window.sessionStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string {
    return sessionStorage.getItem(TOKEN_KEY);
  }

  public getUsername() : string {
    let jsonBody = this.getBodyOfToken();
    return jsonBody.sub;
  }

  public getRoles() : string[] {
    let jsonBody = this.getBodyOfToken();
    return jsonBody.scope;
  }

  public getEmail() : string {
    let jsonBody = this.getBodyOfToken();
    return jsonBody.email;
  }

  public getExpiration() : string {
    let jsonBody = this.getBodyOfToken();
    return jsonBody.exp;
  }

  private getBodyOfToken() : any {
    let token = this.getToken();
    let tokenSections = token.split(".");
    let decodeSection = atob(tokenSections[1]);
    let json = JSON.parse(decodeSection);
    return json;
  }
}