import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../service/schedule.service';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { Schedule } from '../../model/schedule'

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['./schedule-list.component.css']
})
export class ScheduleListComponent implements OnInit {

  daysInWeek: String[] = new Array("Sun", "Mon", "Tue", "Wed", "Thur", "Fri", "Sat");
  monthsInYear: String[] = new Array("January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
  calendarMonth: Date[][] = new Array();

  currentDate: Date = new Date();
  mutableDate: Date = new Date();
  numberOfDaysInMonth: number = 0;
  nothingScheduled: boolean;
  schedule: Schedule[];
  nothingScheduledDate: Date;

  constructor(private scheduleService: ScheduleService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      this.currentDate = new Date(Number(params.get('epoch')));
      this.mutableDate = new Date(Number(params.get('epoch')));
      this.mutableDate.setDate(1);
      this.mutableDate = this.setDateToMidnight(this.mutableDate);
      this.numberOfDaysInMonth = new Date(this.mutableDate.getFullYear(), this.mutableDate.getMonth() + 1, 0).getDate();
      this.buildCalendarMonth();

      this.scheduleService.findAllScheduledByEpoch(Number(params.get('epoch'))).subscribe(data => {
        if (data.length > 0) {
          this.nothingScheduled = false;
          this.schedule = data;
          for (let scheduledItem of this.schedule) {
            var date = new Date(scheduledItem.epoch);
            scheduledItem.epochDay = date.toDateString();
            scheduledItem.uniqueDay = false;
          }

          this.schedule[0].uniqueDay = true;
          for (var i = 0; i < this.schedule.length - 1; i++) {
            if (this.schedule[i].epochDay != this.schedule[i + 1].epochDay) {
              this.schedule[i + 1].uniqueDay = true;
            }
          }
        } else {
          this.nothingScheduled = true;
          this.nothingScheduledDate = new Date(Number(params.get('epoch')));

        }
      })
    })
  }

  findSundayInWeekByDate(date) {
    var day = new Date(date.getTime());
    var dayNum = day.getDay();

    if (dayNum !== 0) {
      day.setHours(-24 * dayNum);
    }
    return day;
  }

  buildCalendarWeek(date) {
    var week = new Array();
    for (var i = 0; i < 7; i++) {
      week.push(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
      date.setHours(24);
    }
    return week;
  }

  buildCalendarMonth() {
    var lastWeek = false;
    var index = 0;

    var sunday = this.findSundayInWeekByDate(this.mutableDate);

    while (lastWeek === false) {
      if (sunday.getDate() + 7 > this.numberOfDaysInMonth && sunday.getMonth() == this.currentDate.getMonth()) {
        lastWeek = true;
      }
      this.calendarMonth[index] = this.buildCalendarWeek(sunday);
      index++;
    }
  }

  nextMonth() {
    if (this.currentDate.getMonth() == 11) {
      this.currentDate.setFullYear(this.currentDate.getFullYear() + 1, 0, 1);
    } else {
      this.currentDate.setMonth(this.currentDate.getMonth() + 1, 1);
    }
    this.currentDate = this.setDateToMidnight(this.currentDate);
    this.cleanUpMonth();
    this.router.navigate(['', this.currentDate.getTime().toString()]);
  }

  previousMonth() {
    if (this.currentDate.getMonth() == 0) {
      this.currentDate.setFullYear(this.currentDate.getFullYear() - 1, 11, 1);
    } else {
      this.currentDate.setMonth(this.currentDate.getMonth() - 1, 1);
    }
    this.currentDate = this.setDateToMidnight(this.currentDate);
    this.cleanUpMonth();
    this.router.navigate(['', this.currentDate.getTime().toString()]);
  }
  
  cleanUpMonth() {
    var d = document.getElementsByClassName("tablerows");

    for(var i = 0; i < d.length; i++) {
      d[i].innerHTML = '';
    }
  }

  setDateToMidnight(date) {
    date.setMinutes(0);
    date.setHours(0);
    date.setSeconds(0);
    return date;
  }
}
