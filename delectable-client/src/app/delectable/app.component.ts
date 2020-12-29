import { Component } from '@angular/core';
import { AuthService } from './login/user_auth/auth.service';
import { TokenStorageService } from './login/user_auth/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string;
  isLoggedIn: boolean = false;

  constructor(private authService: AuthService, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  logout(): void {
    this.tokenStorageService.clearSession();
    window.location.reload();
  }
}
