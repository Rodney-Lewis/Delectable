import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from 'app/delectable/user/service/auth.service';
import { ScheduleService } from '../../service/schedule.service';
import { DateHelper } from '../../service/date-helper';
import { Role } from 'app/delectable/user/model/Role';
import { DatetrackerService } from '../../service/datetracker.service';

@Component({
  selector: 'app-schedule-week-at-a-glance',
  templateUrl: './schedule-week-at-a-glance.component.html',
  styleUrls: ['./schedule-week-at-a-glance.component.css']
})
export class ScheduleWeekAtAGlanceComponent implements OnInit {

  week: Date[];
  items: any[][];
  isLoggedIn: any;
  sunday: Date;
  saturday: Date;
  itemsScheduledByDate: any[][];
  dates: Date[];
  hasUserPermissions: boolean = false;

  constructor(private scheduleService: ScheduleService, private authService: UserAuthService, private router: Router, private dateTracker: DatetrackerService) { }

  ngOnInit(): void {
    this.hasUserPermissions = this.authService.hasPermissions(Role[Role.ROLE_USER]);
    this.isLoggedIn = this.authService.isLoggedIn();

    this.sunday = new Date(this.dateTracker.value)
    this.sunday = DateHelper.setDateToMidnight(this.sunday);
    this.sunday = DateHelper.findSundayInWeekByDate(this.sunday);
    this.saturday = new Date(this.sunday);
    this.saturday.setHours((6 * 24) + 1);

    this.week = new Array();
    this.week = DateHelper.buildWeekFromDate(this.sunday);

    this.scheduleService.findAllBetweenEpochs(this.sunday.toISOString().substring(0, 10), this.saturday.toISOString().substring(0, 10)).subscribe(res => {
      this.itemsScheduledByDate = JSON.parse(res).content;
      this.dates = DateHelper.buildDatesBetweenDates(this.sunday, this.saturday);
    })
  }

  nextWeek() {
    this.dateTracker.value.setDate(this.sunday.getDate() + 7);
    this.ngOnInit();
  }

  previousWeek() {
    this.dateTracker.value.setDate(this.sunday.getDate() - 7);
    this.ngOnInit();
  }

  today() {
    this.dateTracker.value = new Date();
    this.ngOnInit();
  }

  delete(id) {
    this.scheduleService.delete(id).subscribe(() => {
      this.ngOnInit();
    })
  }

}
