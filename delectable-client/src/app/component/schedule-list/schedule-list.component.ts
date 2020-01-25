import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../service/schedule.service';
import { Schedule } from '../../model/schedule';
import { ActivatedRoute } from '@angular/router';

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

  year: number = new Date().getFullYear();
  month: number = new Date().getMonth();
  numberOfDaysInMonth: number = new Date(this.year, this.month, 0).getDate();

  nothingScheduled: boolean;
  nothingScheduledDate: Date;

  constructor(private scheduleService: ScheduleService, private activatedroute: ActivatedRoute) { }

  ngOnInit() {

    this.buildCalendarMonth();

    this.activatedroute.paramMap.subscribe(params => {
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
    var date = new Date();
    var lastWeek = false;
    var index = 0;

    date.setHours(0);
    date.setMinutes(0);
    date.setDate(0);
    var sunday = this.findSundayInWeekByDate(date);

    while (lastWeek === false) {
      this.calendarMonth[index] = this.buildCalendarWeek(sunday);
      index++;

      if (sunday.getDate() + 7 >= this.numberOfDaysInMonth) {
        lastWeek = true;
        this.calendarMonth[index] = this.buildCalendarWeek(sunday);
      }
    }
  }
}
