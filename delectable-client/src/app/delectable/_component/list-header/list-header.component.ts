import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'app/delectable/login/user_auth/auth.service';

@Component({
  selector: 'app-list-header',
  templateUrl: './list-header.component.html',
  styleUrls: ['./list-header.component.css']
})
export class ListHeaderComponent {

  @Input() addItemRoute: string;
  @Input() addButtonText: string;
  @Input() headerText: string;
  @Input() listItemRoute: string;

  searchForm: any = {};
  isLoggedIn: any;

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.isLoggedIn = this.authService.isLoggedIn();
  }

  onSubmit(): void {
    this.router.navigate([this.listItemRoute], { queryParams: { 'cp': 1, 's': this.searchForm.query } })
  }
  
}
