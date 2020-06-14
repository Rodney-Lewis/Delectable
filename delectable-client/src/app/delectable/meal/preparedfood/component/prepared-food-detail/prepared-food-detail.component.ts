import { Component, OnInit } from '@angular/core';
import { PreparedFoodService } from '../../prepared-food.service';
import { FileHandlerService } from 'app/delectable/imagehandler/file-handler.service';
import { ActivatedRoute, Router } from '@angular/router';
import { PreparedFood } from '../../prepared-food';

@Component({
  selector: 'app-prepared-food-detail',
  templateUrl: './prepared-food-detail.component.html',
  styleUrls: ['./prepared-food-detail.component.css']
})
export class PreparedFoodDetailComponent implements OnInit {

  preparedFood: PreparedFood = new PreparedFood()
  thumbnailURL: String;
  totalTimeHour: number = 0;
  totalTimeMinute: number = 0;
  totalTimeSecond: number = 0;

  constructor(private preparedFoodService: PreparedFoodService, private fileHandlerService: FileHandlerService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      this.preparedFoodService.findById(Number(params.get('id'))).subscribe(data => {
        this.preparedFood = data;
        this.thumbnailURL = this.fileHandlerService.getNamedImageUrl(this.preparedFood.imageSource);
        if (this.preparedFood.cookTimeSecond + this.preparedFood.prepTimeSecond > 60) {
          this.totalTimeMinute++;
          this.totalTimeSecond = ((this.preparedFood.cookTimeSecond + this.preparedFood.prepTimeSecond) % 60);
        } else {
          this.totalTimeSecond = (this.preparedFood.cookTimeSecond + this.preparedFood.prepTimeSecond);
        }
        if (this.preparedFood.cookTimeMinute + this.preparedFood.prepTimeMinute > 60) {
          this.totalTimeHour++;
          this.totalTimeMinute = this.totalTimeMinute + ((this.preparedFood.cookTimeMinute + this.preparedFood.prepTimeMinute) % 60);
        } else {
          this.totalTimeMinute = (this.preparedFood.cookTimeMinute + this.preparedFood.prepTimeMinute);
        }
        this.totalTimeHour = this.totalTimeHour + this.preparedFood.cookTimeHour + this.preparedFood.prepTimeHour;
      })
    })
  }

  delete(id: number) {
    this.preparedFoodService.delete(id).subscribe(() => {
      this.router.navigate(['/prepared/list']);
    })
  }

  displayCookTimeHour() {
    return this.preparedFood.cookTimeHour > 0;
  }

  displayCookTimeMinute() {
    return this.preparedFood.cookTimeMinute > 0;
  }

  displayCookTimeSecond() {
    return this.preparedFood.cookTimeSecond > 0;
  }

  displayPrepTimeHour() {
    return this.preparedFood.prepTimeHour > 0;
  }

  displayPrepTimeMinute() {
    return this.preparedFood.prepTimeMinute > 0;
  }

  displayPrepTimeSecond() {
    return this.preparedFood.prepTimeSecond > 0;
  }

  displayDeleteButton() {
    return !this.preparedFood.deleted;
  }

}
