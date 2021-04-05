import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FileHandlerService } from 'app/delectable/shared/service/file-handler.service';
import { Role } from 'app/delectable/user/model/Role';
import { AuthService } from 'app/delectable/user/service/auth.service';
import { Restaurant } from '../../model/restaurant';
import { RestaurantService } from '../../service/restaurant.service';

@Component({
  selector: 'app-restaurant-detail',
  templateUrl: './restaurant-detail.component.html',
  styleUrls: ['./restaurant-detail.component.css']
})
export class RestuarantDetailComponent implements OnInit {

  restaurant: Restaurant = new Restaurant();
  hasUserPermissions: boolean = false;
  constructor(private authService: AuthService, private restaurantService: RestaurantService, private activatedroute: ActivatedRoute, private router: Router, private fileHandlerService: FileHandlerService) { }

  ngOnInit(): void {
    this.hasUserPermissions = this.authService.hasPermissions(Role[Role.ROLE_USER]);

    this.activatedroute.paramMap.subscribe(params => {
      this.restaurantService.getById(Number(params.get('id'))).subscribe(data => {
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
