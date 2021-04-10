import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RecipeService } from 'app/delectable/recipe/service/recipe.service';
import { UserAuthService } from 'app/delectable/user/service/auth.service';
import { RestaurantService } from 'app/delectable/restaurant/service/restaurant.service';
import { ScheduleService } from '../../service/schedule.service';
import { DateHelper } from '../../service/date-helper';
import { Role } from 'app/delectable/user/model/Role';

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
  hasUserPermissions: boolean = false;

  constructor(private scheduleService: ScheduleService, private restaurantService: RestaurantService,
    private recipeService: RecipeService, private authService: UserAuthService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.hasUserPermissions = this.authService.hasPermissions(Role[Role.ROLE_USER]);

    this.activatedroute.paramMap.subscribe(params => {
      if (!isNaN(Number(params.get('epoch')))) {
        this.startDate = new Date(Number(params.get('epoch')));
        this.startDate = DateHelper.setDateToMidnight(this.startDate);
        this.startDate = DateHelper.findSundayInWeekByDate(this.startDate);
        this.week = new Array();
        this.week = DateHelper.buildWeekFromDate(this.startDate);
        this.endDate = new Date(this.startDate);
        //Push the date forward 6 days and add 1 hour to handle time shift of daylight savings
        this.endDate.setHours((6 * 24) + 1);
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
    this.router.navigate(['schedule/' + epoch]);
  }

  today() {
    this.router.navigate(['/schedule']);
  }

  delete(id) {
    this.scheduleService.delete(id).subscribe(() => {
      this.ngOnInit();
    })
  }

}
