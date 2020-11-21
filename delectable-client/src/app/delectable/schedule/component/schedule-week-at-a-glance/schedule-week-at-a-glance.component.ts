import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { RestaurantService } from 'app/delectable/restaurant/restaurant.service';
import { ScheduleService } from '../../schedule.service';
import { DateHelper } from '../../date-helper';

@Component({
  selector: 'app-schedule-week-at-a-glance',
  templateUrl: './schedule-week-at-a-glance.component.html',
  styleUrls: ['./schedule-week-at-a-glance.component.css']
})
export class ScheduleWeekAtAGlanceComponent implements OnInit {

  startDate: Date;
  week: Date[];
  items: any[][];
  itemsScheduled: any[][];

  get WEEKDAYS() { return DateHelper.WEEKDAYS }
  get MONTHS() { return DateHelper.MONTHS }

  constructor(private scheduleService: ScheduleService, private restaurantService: RestaurantService, private recipeService: RecipeService) { }

  ngOnInit(epoch?: number): void {

    if (epoch) {
      this.startDate = new Date(epoch);
    } else {
      this.startDate = new Date();
    }

    var sunday = DateHelper.findSundayInWeekByDate(this.startDate);
    sunday = DateHelper.setDateToMidnight(sunday);
    this.week = new Array();
    this.week = DateHelper.buildWeekFromDate(sunday);

    this.scheduleService.findAllBetweenEpochs(this.week[0].getTime(), this.week[6].getTime()).subscribe(res => {
      var scheduledByWeek = JSON.parse(res).content;
      var scheduledByDate = new Array();
      this.items = new Array();
      this.itemsScheduled = new Array();

      for (let i = 0; i < this.week.length; i++) {
        scheduledByDate.splice(0, scheduledByDate.length);
        scheduledByWeek.forEach(element => {
          if (element.epoch === this.week[i].getTime()) {
            scheduledByDate.push(element);
          }
        });

        this.itemsScheduled.push(new Array());
        if (scheduledByDate.length != 0) {
          scheduledByDate.forEach(element => {
            this.itemsScheduled[i].push(element);
          })
        }

        this.items.push(new Array());
        for (let j = 0; j < scheduledByDate.length; j++) {
          if (scheduledByDate[j].scheduleType.toLowerCase() == "recipe") {
            this.recipeService.findById(scheduledByDate[j].scheduledTypeId).subscribe(data => {
              this.items[i].push(data);
            })
          } else if (scheduledByDate[j].scheduleType.toLowerCase() == "restaurant") {
            this.restaurantService.findById(scheduledByDate[j].scheduledTypeId).subscribe(data => {
              this.items[i].push(data);
            })
          }
        }
      }
    })
  }

  nextWeek() {
    this.startDate.setDate(this.startDate.getDate() + 7)
    this.changeWeek(this.startDate.getTime());
  }

  previousWeek() {
    this.startDate.setDate(this.startDate.getDate() - 7)
    this.changeWeek(this.startDate.getTime());
  }

  changeWeek(epoch: number) {
    this.ngOnInit(epoch);
  }

  today() {
    this.ngOnInit();
  }

  delete(id) {
    this.scheduleService.delete(id).subscribe(() => {
      this.ngOnInit(this.startDate.getTime());
    })
  }

}
