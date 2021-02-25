import { Component } from '@angular/core';
import { AuthService } from './login/user_auth/auth.service';
import { UserService } from './user-settings/service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string;
  isLoggedIn: boolean = false;
  user: any;

  constructor(private authService: AuthService, private userService: UserService) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
    if (this.isLoggedIn) {
      this.userService.getCurrentUserDetails().subscribe(data => {
        this.user = data;
      });
    }
  }
}
