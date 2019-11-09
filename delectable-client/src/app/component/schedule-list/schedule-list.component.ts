import { Component, OnInit } from '@angular/core';
import { ScheduleService } from '../../service/schedule.service';
import { Schedule } from '../../model/schedule';

@Component({
  selector: 'app-schedule-list',
  templateUrl: './schedule-list.component.html',
  styleUrls: ['./schedule-list.component.css']
})
export class ScheduleListComponent implements OnInit {

  schedules: Schedule[];
  constructor(private scheduleService: ScheduleService) { }

  ngOnInit() {
    this.scheduleService.findAllScheduled().subscribe(data => {
      this.schedules = data;

      for(let schedule of this.schedules) {
        var date = new Date(schedule.epoch);
        schedule.epochDay = date.toDateString();
        schedule.uniqueDay = false;
      }

      this.schedules[0].uniqueDay = true;
      for(var i = 0; i < this.schedules.length - 1; i++) {
        if(this.schedules[i].epochDay != this.schedules[i+1].epochDay) {
          this.schedules[i+1].uniqueDay = true;
        }
      }
    })
  }

}
