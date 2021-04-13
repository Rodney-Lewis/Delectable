import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormArray, FormBuilder, Validators } from '@angular/forms';
import { Schedule } from 'app/delectable/schedule/model/schedule';
import { FormComponent } from 'app/delectable/shared/component/form/base-form/form-component';
import { ScheduleService } from '../../service/schedule.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.css']
})
export class ScheduleFormComponent extends FormComponent implements OnInit {
  mealTimes: string[];
  scheduleTypes: string[];
  schedulableItems: any[];
  schedule: Schedule;
  formSubmitted: boolean = false;

  constructor(formBuilder: FormBuilder, private scheduleService: ScheduleService, router: Router) {
    super(router, formBuilder);
  }

  ngOnInit() {
    this.buildFormforCreate();
    this.schedulableItems = new Array();
    forkJoin([
      this.scheduleService.getScheduleable("", 10),
      this.scheduleService.getAllScheduleTypes(),
      this.scheduleService.getAllMealTypes()
    ]).subscribe(([schedulableItems, scheduleTypes, mealTimes]) => {
      this.schedulableItems = JSON.parse(schedulableItems).content;
      this.scheduleTypes = scheduleTypes;
      this.mealTimes = mealTimes;
    })
  }

  searchOnType(event) {
    this.scheduleService.getScheduleable(event.target.value, 10).subscribe(data => {
      this.schedulableItems = [];
      this.schedulableItems = JSON.parse(data).content;
    })
  }

  buildFormforCreate() {
    let formGroup = this.getFormGroupComponent('element');
    formGroup.addControl('schedules', new FormArray([]));
  }

  addSchedule(index: number) {
    this.getFormArrayComponent('element.schedules').push(this.formBuilder.group({
      scheduledItemName: [this.schedulableItems[index].name],
      date: [new Date().toISOString().substring(0, 10), Validators.required],
      mealTime: [this.mealTimes[0], Validators.required],
      scheduleType: [this.schedulableItems[index].scheduleType, Validators.required],
      scheduledItemId: [this.schedulableItems[index].id, Validators.required]
    }));
  }

  removeSchedule(index: number) {
    this.getFormArrayComponent('element.schedules').removeAt(index);
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.form.invalid) {
      return;
    } else {
      this.scheduleService.add(this.getFormComponent("element.schedules").value).subscribe(() => {
        this.router.navigate(['']);
      })
    }
  }

  buildFormforEdit(id: number) {
    throw new Error("Method not implemented.");
  }

}
