import { Component, OnInit } from '@angular/core';
import { Restaurant } from '../../model/restaurant';
import { RestaurantService } from '../../service/restaurant.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  restaurants: Restaurant[];
  constructor(private restaurantService: RestaurantService) { }

  ngOnInit() {
    this.restaurantService.findAll().subscribe(data => {
      this.restaurants = data;
    })
  }

}
