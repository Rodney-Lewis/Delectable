import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { FileHandlerService } from 'app/delectable/shared/service/file-handler.service';
import { RestaurantService } from '../../service/restaurant.service';

@Component({
  selector: 'app-restaurant-list',
  templateUrl: './restaurant-list.component.html',
  styleUrls: ['./restaurant-list.component.css']
})
export class RestaurantListComponent implements OnInit {

  constructor(private restaurantService: RestaurantService, private filehandler: FileHandlerService, private activatedRoute: ActivatedRoute) {
  }

  jsonResponse: any;
  pageSize: number = 8;

  ngOnInit() {
    this.activatedRoute.queryParams.subscribe(params => {
      if(params.ps)
        this.pageSize = params.ps;

      this.restaurantService.getPage(params.cp, this.pageSize, params.s).subscribe(data => {
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
