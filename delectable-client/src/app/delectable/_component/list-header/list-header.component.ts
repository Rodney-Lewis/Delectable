import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-list-header',
  templateUrl: './list-header.component.html',
  styleUrls: ['./list-header.component.css']
})
export class ListHeaderComponent {

  @Input() addItemRoute: string;
  @Input() addButtonText: string;
  @Input() headerText: string;
  
}
