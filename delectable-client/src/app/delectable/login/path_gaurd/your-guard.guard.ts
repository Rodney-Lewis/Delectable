import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../user_auth/auth.service';
import { TokenStorageService } from '../user_auth/token-storage.service';


@Injectable({
  providedIn: 'root'
})
export class YourGuardGuard implements CanActivate {

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService, private router: Router) { }

  canActivate(
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const url: string = state.url;
    return this.checkLogin(url);
  }

  checkLogin(url: string): true | UrlTree {
    if (this.authService.isLoggedIn()) {
      return true;
    } else {
      this.authService.redirectUrl = url;
      this.tokenStorage.clearSession();
      return this.router.parseUrl('/login');
    }
  }

}

