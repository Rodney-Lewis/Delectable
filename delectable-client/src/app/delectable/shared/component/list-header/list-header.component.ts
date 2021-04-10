import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Role } from 'app/delectable/user/model/Role';
import { UserAuthService } from 'app/delectable/user/service/auth.service';

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
  hasUserPermissions: boolean = false;

  constructor(private authService: UserAuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.hasUserPermissions = this.authService.hasPermissions(Role[Role.ROLE_USER]);
  }

  onSubmit(): void {
    this.router.navigate([this.listItemRoute], { queryParams: { 'cp': 1, 's': this.searchForm.query } })
  }

}
