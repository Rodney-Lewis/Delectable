import { Injectable } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, UrlTree, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { Role } from '../model/Role';
import { AuthService } from '../service/auth.service';
import { TokenStorageService } from '../service/token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class YourGuardGuard implements CanActivate {

  constructor(private tokenStorage: TokenStorageService, private authService: AuthService, private router: Router) { }

  canActivate(next: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const userRoles = this.tokenStorage.getRoles();

    if (this.authService.isLoggedIn() && next.data) {
      if (this.authService.hasPermissions(next.data))
        return this.authService.hasPermissions(next.data)
      else {
        return this.router.parseUrl('/');
      }
    } else {
      return this.router.parseUrl('/');
    }
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

