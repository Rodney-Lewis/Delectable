import { Component, Input } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-toolbar',
  templateUrl: './list-toolbar.component.html',
  styleUrls: ['./list-toolbar.component.css'],
})
export class ListToolbarComponent {

  searchForm: any = {};
  @Input() listItemRoute: string;

  constructor(private router: Router) { }

  onSubmit(): void {
    this.router.navigate([this.listItemRoute], { queryParams: { 'cp': 1, 's': this.searchForm.query } })
  }
}
