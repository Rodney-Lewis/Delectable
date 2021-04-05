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

  canActivate (
    next: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): true | UrlTree {
    const url: string = state.url;
    const requiredRole: any = next.data.role;
    return this.checkLogin(url, requiredRole);
  }

  checkLogin(url: string, requiredRole: any) : true | UrlTree {

    var loggedIn = this.authService.isLoggedIn();
    var hasPermissions = this.authService.hasPermissions(requiredRole);

    if (loggedIn) {
      if(hasPermissions) {
        return true;
      } else {
        return this.router.parseUrl('');
      }
    } else {
      // Store the attempted URL for redirecting
      this.authService.redirectUrl = url;
      // Redirect to the login page
      return this.router.parseUrl('/login');
    }
  }
}

