import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { CRUDService } from 'app/delectable/shared/service/crud.service';

const restaurantAPIEndpoint: string = "/api/restaurant";

@Injectable({
  providedIn: 'root'
})
export class RestaurantService extends CRUDService {

  constructor(http: HttpClient) {
    super(http, restaurantAPIEndpoint)
  }
  
}
