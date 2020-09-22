import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-pantry-card-info',
  templateUrl: './pantry-card-info.component.html',
  styleUrls: ['./pantry-card-info.component.css']
})
export class PantryCardInfoComponent {

  constructor() { }

  @Input() item: any;
  @Input() detailItemRoute: string;

}
