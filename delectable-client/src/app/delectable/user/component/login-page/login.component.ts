import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../../service/auth.service';
import { TokenStorageService } from '../../service/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private authService: UserAuthService, private tokenStorage: TokenStorageService, private router: Router) { }

  ngOnInit(): void {
    if (this.authService.isLoggedIn()) {
      this.router.navigate(['']);
    }
  }

  onSubmit(): void {
    this.authService.login(this.form).subscribe(
      data => {
        this.tokenStorage.saveToken(data.accessToken);
        this.isLoginFailed = false;
        if (this.authService.redirectUrl != null) {
          this.router.navigate([this.authService.redirectUrl]).then(() => {
            this.authService.redirectUrl = null;
            window.location.reload();
          });
        } else {
          this.router.navigate(['']).then(() => {
            window.location.reload();
          });
        }
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }
}
