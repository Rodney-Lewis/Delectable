import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { Schedule } from 'app/delectable/schedule/model/schedule';
import { FormComponent } from 'app/delectable/shared/component/form/base-form/form-component';
import { Recipe } from 'app/delectable/recipe/model/recipe';
import { RecipeService } from 'app/delectable/recipe/service/recipe.service';
import { RestaurantService } from 'app/delectable/restaurant/service/restaurant.service';
import { ScheduleService } from '../../service/schedule.service';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.css']
})
export class ScheduleFormComponent extends FormComponent implements OnInit {
  epochString: string;
  epochStrings: string[];
  date: Date;
  mealTimes: string[];
  scheduleTypes: string[];

  items: any[];

  recipes: Recipe[];
  schedule: Schedule = new Schedule();
  formSubmitted: boolean = false;

  constructor(formBuilder: FormBuilder, private scheduleService: ScheduleService,
    private recipeService: RecipeService, router: Router, private restaurantService: RestaurantService) {
    super(router, formBuilder);
  }

  ngOnInit() {
    this.scheduleService.getAllMealTypes().subscribe(data => {
      this.mealTimes = data;
    })

    this.scheduleService.getAllScheduleTypes().subscribe(data => {
      this.scheduleTypes = data;
    })
    this.buildFormforCreate();
  }

  onScheduleTypeChange(event) {
    this.items = [];
    if (event.target.value == this.scheduleTypes.find(element => element.toLowerCase() == "restaurant")) {
      this.restaurantService.getPage(1, 1000).subscribe(data => {
        this.items = data.body.content;
      })
    } else if (event.target.value == this.scheduleTypes.find(element => element.toLowerCase() == "recipe")) {
      this.recipeService.getPage(1, 1000).subscribe(data => {
        this.items = data.body.content;
      })
    }
  }

  submitForm() {
    this.formSubmitted = true;
    if (this.form.invalid) {
      return;
    } else {
      this.epochString = this.form.get("epochDay").value;
      this.epochStrings = this.epochString.split("-");
      this.date = new Date(parseInt(this.epochStrings[0]), parseInt(this.epochStrings[1]) - 1, parseInt(this.epochStrings[2]));
      this.schedule.epoch = this.date.getTime();
      this.schedule.mealTime = this.form.get("mealTime").value;
      this.schedule.scheduleType = this.form.get("scheduleType").value;
      this.schedule.scheduledItemId = this.form.get("scheduleTypeId").value;

      this.items.forEach(element => {
        if (element.id == this.schedule.scheduledItemId)
          this.schedule.scheduledItemName = element.name;
      });

      this.scheduleService.add(this.schedule).subscribe(() => {
        this.router.navigate(['']);
      })
    }
  }

  buildFormforEdit(id: number) {
    throw new Error("Method not implemented.");
  }

  buildFormforCreate() {
    this.form = this.formBuilder.group({
      epochDay: [new Date().toISOString().substring(0, 10), Validators.required],
      mealTime: ['', Validators.required],
      scheduleType: ['', Validators.required],
      scheduleTypeId: ['', Validators.required]
    });
  }
}
