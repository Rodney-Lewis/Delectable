import { Component, OnInit, Input, Output } from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html',
  styleUrls: ['./pagination.component.css']
})
export class PaginationComponent implements OnInit {

  @Input() logicalCurrentPage: number;
  @Input() pageSize: number;
  @Input() totalPages: number;
  @Input() totalElements: number;
  @Input() listItemRoute: string;
  displayedCurrentPage: number;
  breadcrumbOffset: number = 3; // Required gap in between numbers to display breadcrumbs
  numberOfLinks: number = 3 // Number of links possible in between the breadcrumb offset
  pages: any[] = new Array();
  nextPage: number;
  previousPage: number;

  constructor() {
  }

  ngOnInit() {
    this.buildPagination();
    this.buildNextAndPreviousPagesLinks();
  }

  private hasLeftOffset(): boolean {
    return (Math.abs((this.displayedCurrentPage - 1)) > this.breadcrumbOffset) && this.totalPages > 5;
    // 1 -> 0 > 3
    // 2 -> 1 > 3
    // 3 -> 2 > 3
    // 4 -> 3 > 3
    // 5 -> 4 > 3
  }

  private hasRightOffset(): boolean {
    return (Math.abs((this.displayedCurrentPage - this.totalPages)) > this.breadcrumbOffset && this.totalPages > 5);
    //10 -> 0 > 3
    //9 -> 1 > 3
    //8 -> 2 > 3
    //7 -> 3 > 3
    //6 -> 4 > 3
  }

  isActivePage(pageNumber: number): boolean {
    return (pageNumber == this.displayedCurrentPage);
  }

  private buildPagination() {
    this.pages.splice(0);
    this.displayedCurrentPage = this.logicalCurrentPage + 1;
    if (this.hasLeftOffset() && this.hasRightOffset()) {
      this.pages.push(1);
      this.pages.push('offset');
      let indexStart = this.displayedCurrentPage - 1;
      for (let index = indexStart; index < (this.numberOfLinks + indexStart); index++) {
        this.pages.push(index);
      }
      this.pages.push('offset');
      this.pages.push(this.totalPages);
    }
    else if (this.hasLeftOffset()) {
      this.pages.push(this.totalPages);
      let pageStart = this.totalPages - 1;
      for (let index = pageStart; index > (pageStart - this.numberOfLinks); index--) {
        this.pages.push(index);
      }
      this.pages.push('offset');
      this.pages.push(1);
      this.pages = this.pages.reverse();
    }
    else if (this.hasRightOffset()) {
      this.pages.push(1);
      let pageStart = 2;
      for (let index = pageStart; index < (pageStart + this.numberOfLinks); index++) {
        this.pages.push(index);
      }
      this.pages.push('offset');
      this.pages.push(this.totalPages);
    }
    else {
      for (let index = 1; index <= this.totalPages; index++) {
        this.pages.push(index);
      }
    }
  }

  private buildNextAndPreviousPagesLinks() {
    if (this.displayedCurrentPage == 1 && this.totalPages == 1) {
      this.nextPage = null;
      this.previousPage = null;
    }
    else if (this.displayedCurrentPage == 1) {
      this.nextPage = this.displayedCurrentPage + 1;
      this.previousPage = null;
    }
    else if (this.displayedCurrentPage == this.totalPages) {
      this.nextPage = null;
      this.previousPage = this.displayedCurrentPage - 1;
    }
    else {
      this.nextPage = this.displayedCurrentPage + 1;
      this.previousPage = this.displayedCurrentPage - 1;
    }
  }

  ngOnChanges() {
    this.ngOnInit();
  }
}
