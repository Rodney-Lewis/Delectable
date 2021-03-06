import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Role } from './user/model/Role';
import { UserAuthService } from './user/service/auth.service';
import { UserAccountService } from './user/service/user.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {

  title: string;
  isLoggedIn: boolean = false;
  user: any;
  hasAdminPermissions: boolean = false;

  constructor(private authService: UserAuthService, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(() => {
      this.isLoggedIn = this.authService.isLoggedIn();
      this.hasAdminPermissions = this.authService.hasPermissions(Role[Role.ROLE_ADMIN]);
    })
  }
}
