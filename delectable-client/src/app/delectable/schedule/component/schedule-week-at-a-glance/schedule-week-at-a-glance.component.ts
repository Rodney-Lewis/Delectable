import { Component, OnInit } from '@angular/core';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { RestaurantService } from 'app/delectable/restaurant/restaurant.service';
import { ScheduleService } from '../../schedule.service';
import { DateHelper } from '../../date-helper';
import { AuthService } from 'app/delectable/login/user_auth/auth.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-schedule-week-at-a-glance',
  templateUrl: './schedule-week-at-a-glance.component.html',
  styleUrls: ['./schedule-week-at-a-glance.component.css']
})
export class ScheduleWeekAtAGlanceComponent implements OnInit {

  week: Date[];
  items: any[][];
  isLoggedIn: any;
  startDate: Date;
  endDate: Date;
  itemsScheduledByDate: any[][];
  dates: Date[];

  constructor(private scheduleService: ScheduleService, private restaurantService: RestaurantService,
    private recipeService: RecipeService, private authService: AuthService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {

    this.activatedroute.paramMap.subscribe(params => {
      if (!isNaN(Number(params.get('epoch')))) {
        this.startDate = new Date(Number(params.get('epoch')));
        this.startDate = DateHelper.setDateToMidnight(this.startDate);
        this.startDate = DateHelper.findSundayInWeekByDate(this.startDate);
        this.week = new Array();
        this.week = DateHelper.buildWeekFromDate(this.startDate);
        this.endDate = new Date(this.startDate);
        this.endDate.setHours(6 * 24);
      } else {
        this.today();
      }

      this.scheduleService.findAllBetweenEpochs(this.startDate.getTime(), this.endDate.getTime()).subscribe(res => {
        this.itemsScheduledByDate = JSON.parse(res).content;
        this.dates = DateHelper.buildDatesBetweenDates(this.startDate, this.endDate);
      })
    })

    this.isLoggedIn = this.authService.isLoggedIn();

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
    this.router.navigate([epoch]);
  }

  today() {
    this.router.navigate(['']);
  }

  delete(id) {
    this.scheduleService.delete(id).subscribe(() => {
      this.ngOnInit();
    })
  }

}
