import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../service/schedule.service';
import { Schedule } from '../../model/schedule';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['./schedule-list.component.css']
})
export class ScheduleListComponent implements OnInit {

  daysInWeek: String[] = new Array("S", "M", "T", "W", "T", "F", "S");
  monthsInYear: String[] = new Array("January", "Feburary", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
  schedule: Schedule[] = new Array();
  calendarMonth: Date[][] = new Array();

  currentDate: Date;
  numberOfDaysInMonth: number = 0;

  nothingScheduled: boolean;
  nothingScheduledDate: Date;

  constructor(private scheduleService: ScheduleService, private activatedroute: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.activatedroute.paramMap.subscribe(params => {
      this.scheduleService.findAllScheduledByEpoch(Number(params.get('epoch'))).subscribe(data => {

        this.currentDate = new Date(Number(params.get('epoch')));
        this.currentDate.setDate(1);
        this.setCurrentDateToMidnight();
        this.numberOfDaysInMonth = new Date(this.currentDate.getFullYear(), this.currentDate.getMonth(), 0).getDate();
        this.buildCalendarMonth();

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
    var day = date.getDay() || 7;
    if (day !== 0) {
      date.setHours(-24 * day);
    }
    return date;
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
    this.setCurrentDateToMidnight();
    var sunday = this.findSundayInWeekByDate(this.currentDate);

    while (lastWeek === false) {
      this.calendarMonth[index] = this.buildCalendarWeek(sunday);
      index++;

      if (sunday.getDate() + 7 >= this.numberOfDaysInMonth) {
        lastWeek = true;
        this.calendarMonth[index] = this.buildCalendarWeek(sunday);
      }
    }
  }

  nextMonth() {
    if(this.currentDate.getMonth() == 11) {
      this.currentDate.setFullYear(this.currentDate.getFullYear() + 1, 0, 1);
    } else {
      this.currentDate.setMonth(this.currentDate.getMonth() + 1, 1);
    }
    this.setCurrentDateToMidnight();
    this.router.navigate(['', this.currentDate.getTime().toString()]);
  }

  previousMonth() {
    if(this.currentDate.getMonth() == 0) {
      this.currentDate.setFullYear(this.currentDate.getFullYear() - 1, 11, 1);
    } else {
      this.currentDate.setMonth(this.currentDate.getMonth() - 1, 1);
    }
    this.setCurrentDateToMidnight()
    this.router.navigate(['', this.currentDate.getTime().toString()]);
  }

  setCurrentDateToMidnight() {
    this.currentDate.setMinutes(0);
    this.currentDate.setHours(0);
    this.currentDate.setSeconds(0);
  }
}
