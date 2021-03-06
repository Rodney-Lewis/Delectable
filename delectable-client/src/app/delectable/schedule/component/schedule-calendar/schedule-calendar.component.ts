import { Component, HostListener, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { UserAuthService } from 'app/delectable/user/service/auth.service';
import { RecipeService } from 'app/delectable/recipe/service/recipe.service';
import { RestaurantService } from 'app/delectable/restaurant/service/restaurant.service';
import { ScheduleService } from '../../service/schedule.service';
import { DateHelper } from '../../service/date-helper';

@Component({
  selector: 'app-schedule-calendar',
  templateUrl: './schedule-calendar.component.html',
  styleUrls: ['./schedule-calendar.component.css']
})
export class ScheduleCalendarComponent implements OnInit {

  startDate: Date;
  endDate: Date;
  firstOfTheMonth: Date;
  numberOfDaysInMonth: number;
  nothingScheduled: boolean;
  dates: Date[];
  itemsScheduledByDate: any[][];
  calendar: Date[][];
  isLoggedIn: boolean;

  lessThan425px: boolean = false;

  get WEEKDAYS() { return DateHelper.WEEKDAYS }
  get MONTHS() { return DateHelper.MONTHS }

  constructor(private authService: UserAuthService, private scheduleService: ScheduleService, private activatedroute: ActivatedRoute, private router: Router, private recipeService: RecipeService, private restaurantService: RestaurantService) { }

  ngOnInit() {
    this.calendar = new Array();

    this.activatedroute.paramMap.subscribe(params => {
      this.isLoggedIn = this.authService.isLoggedIn();
      var dateParameters = new Array();
      dateParameters = params.get('epoch').split("_");
      this.startDate = new Date(Number(dateParameters[0]));
      this.startDate = DateHelper.setDateToMidnight(this.startDate);
      this.firstOfTheMonth = new Date(this.startDate.getTime());
      this.firstOfTheMonth.setDate(1);
      this.numberOfDaysInMonth = new Date(this.firstOfTheMonth.getFullYear(), this.firstOfTheMonth.getMonth() + 1, 0).getDate();
      this.buildCalendarMonth();

      if (dateParameters.length == 2) {
        this.endDate = new Date(Number(dateParameters[1]));
        this.endDate = DateHelper.setDateToMidnight(this.endDate);
      } else if (dateParameters.length == 1) {
        this.endDate = new Date(this.startDate);
      } else {
        this.today();
      }

      this.scheduleService.findAllBetweenEpochs(this.startDate.getTime(), this.endDate.getTime()).subscribe(res => {
        this.itemsScheduledByDate = JSON.parse(res).content;
        this.dates = DateHelper.buildDatesBetweenDates(this.startDate, this.endDate);
        console.log(this.itemsScheduledByDate[0]);
      })
    })

    if(window.innerWidth < 425) {
      this.lessThan425px = true;
    } else {
      this.lessThan425px = false;
    }
  }

  @HostListener('window:resize', ['$event'])
  getScreenSize(event?) {
    if(window.innerWidth < 425) {
      this.lessThan425px = true;
    } else {
      this.lessThan425px = false;
    }
  }


  buildCalendarMonth() {
    this.cleanUpMonth();
    var lastWeek = false;
    var index = 0;
    var sunday = DateHelper.findSundayInWeekByDate(this.firstOfTheMonth);

    while (lastWeek === false) {
      if (sunday.getDate() + 7 > this.numberOfDaysInMonth && sunday.getMonth() == this.firstOfTheMonth.getMonth()) {
        lastWeek = true;
      }
      this.calendar[index] = DateHelper.buildWeekFromDate(sunday);
      index++;
    }
  }

  nextMonth() {
    if (this.startDate.getMonth() == 11) {
      this.startDate.setFullYear(this.startDate.getFullYear() + 1, 0, 1);
    } else {
      this.startDate.setMonth(this.startDate.getMonth() + 1, 1);
    }
    this.cleanUpMonth();
    this.router.navigate(['', this.startDate.getTime()]);
  }

  previousMonth() {
    if (this.startDate.getMonth() == 0) {
      this.startDate.setFullYear(this.startDate.getFullYear() - 1, 11, 1);
    } else {
      this.startDate.setMonth(this.startDate.getMonth() - 1, 1);
    }
    this.cleanUpMonth();
    this.router.navigate(['', this.startDate.getTime()]);
  }

  today() {
    this.router.navigate(['']);
  }

  cleanUpMonth() {
    var d = document.getElementsByClassName("tablerows");

    for (var i = 0; i < d.length; i++) {
      d[i].innerHTML = '';
    }
  }

  delete(id) {
    this.scheduleService.delete(id).subscribe(() => {
      this.ngOnInit();
    })
  }
}