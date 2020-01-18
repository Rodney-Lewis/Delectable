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
  monthsString: String[] = new Array("January", "Feburary", "March", "April", "May", "June",
    "July", "August", "September", "October", "November", "December");
  schedules: Schedule[];
  builtMonth: Date[][] = new Array();
  year: number = new Date().getFullYear();
  month: number = new Date().getMonth();
  daysInMonth: number = new Date(this.year, this.month, 0).getDate();

  yearNum: number;
  monthNum: number;
  dayNum: number;

  constructor(private scheduleService: ScheduleService, private activatedroute: ActivatedRoute) { }

  ngOnInit() {

    this.buildMonth();

    this.activatedroute.paramMap.subscribe(params => {
      this.scheduleService.findAllScheduledByEpoch(Number(params.get('epoch'))).subscribe(data => {
        if (data.length > 0) {
          this.schedules = data;
          for (let schedule of this.schedules) {
            var date = new Date(schedule.epoch);
            schedule.epochDay = date.toDateString();
            schedule.uniqueDay = false;
          }

          this.schedules[0].uniqueDay = true;
          for (var i = 0; i < this.schedules.length - 1; i++) {
            if (this.schedules[i].epochDay != this.schedules[i + 1].epochDay) {
              this.schedules[i + 1].uniqueDay = true;
            }
          }
        } else {
          this.schedules.push("Nothing scheduled!");
        }
      })
    })
  }

  findSunday(date) {
    var day = date.getDay() || 7;
    if (day !== 0) {
      date.setHours(-24 * day);
    }
    return date;
  }

  buildWeek(date) {
    var week = new Array();
    for (var i = 0; i < 7; i++) {
      week.push(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
      date.setHours(24);
    }
    return week;
  }

  buildMonth() {
    var date = new Date();
    var lastWeek = false;
    var index = 0;

    date.setHours(0);
    date.setMinutes(0);
    date.setDate(0);
    var sunday = this.findSunday(date);

    while (lastWeek === false) {
      this.builtMonth[index] = this.buildWeek(sunday);
      index++;

      if (sunday.getDate() + 7 >= this.daysInMonth) {
        lastWeek = true;
        this.builtMonth[index] = this.buildWeek(sunday);
      }
    }
    console.log(this.builtMonth);
  }
}
