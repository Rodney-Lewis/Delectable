import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'app/delectable/login/user_auth/auth.service';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { Restaurant } from '../../restaurant';
import { RestaurantService } from '../../restaurant.service';

@Component({
  selector: 'app-restaurant-detail',
  templateUrl: './restaurant-detail.component.html',
  styleUrls: ['./restaurant-detail.component.css']
})
export class RestuarantDetailComponent implements OnInit {

  restaurant: Restaurant = new Restaurant();
  constructor(private authService: AuthService, private restaurantService: RestaurantService, private activatedroute: ActivatedRoute, private router: Router, private fileHandlerService: FileHandlerService) { }

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      this.restaurantService.findById(Number(params.get('id'))).subscribe(data => {
        this.restaurant = data;
        this.restaurant.imageSource = this.fileHandlerService.getNamedImageUrl(this.restaurant.imageSource);
      })
    })
  }

  delete(id) {
    this.restaurantService.delete(id).subscribe(data => {
      this.router.navigate(['/restaurant/list']);
    })
  }

  displayDeleteButton() {
    return ((!this.restaurant.deleted) && this.authService.isLoggedIn());
  }

}
