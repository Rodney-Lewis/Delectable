import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-restaurant-card-info',
  templateUrl: './restaurant-card-info.component.html',
  styleUrls: ['./restaurant-card-info.component.css', '/app/delectable/shared/component/carddeck/carddeck.component.css']
  
})
export class RestaurantCardInfoComponent {

  @Input() item: any;
  @Input() detailItemRoute: string;

}
