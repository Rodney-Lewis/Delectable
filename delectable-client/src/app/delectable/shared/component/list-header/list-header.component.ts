import { Component, Input } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/delectable/user/service/auth.service';

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
  displayAddButton: boolean = false;

  constructor(private authService: AuthService, private router: Router, private activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(data => {
      this.displayAddButton = this.authService.hasPermissions(data);
    })
  }

  onSubmit(): void {
    this.router.navigate([this.listItemRoute], { queryParams: { 'cp': 1, 's': this.searchForm.query } })
  }
  
}
