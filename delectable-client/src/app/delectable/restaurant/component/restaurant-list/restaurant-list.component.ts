import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';
import { RestaurantService } from '../../restaurant.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  constructor(private restaurantService: RestaurantService, private filehandler: FileHandlerService, private activatedRoute: ActivatedRoute) {
  }

  jsonResponse: any;

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      this.restaurantService.findAll(params.cp, params.ps, params.s).subscribe(data => {
        this.jsonResponse = JSON.parse(data);
        this.jsonResponse.content.forEach(restaurant => {
          restaurant.imageSource = this.filehandler.getNamedImageUrl(restaurant.imageSource);
        });
      },
        err => {
          this.jsonResponse = JSON.parse(err.error).message;
        }
      );
    })
  }
}
