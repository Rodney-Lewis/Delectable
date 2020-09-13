import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ScheduleService } from 'app/delectable/schedule/schedule.service';
import { Schedule } from 'app/delectable/schedule/schedule';
import { Recipe } from 'app/delectable/meal/recipe/recipe';
import { RecipeService } from 'app/delectable/meal/recipe/recipe.service';
import { FormComponent } from 'app/delectable/component/form-component';
import { RestaurantService } from 'app/delectable/restaurant/service/restaurant.service';
import { PreparedFoodService } from 'app/delectable/meal/preparedfood/prepared-food.service';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.css']
})
export class ScheduleFormComponent extends FormComponent implements OnInit {
  epochString: string;
  epochStrings: string[];
  date: Date;
  mealTypes: string[];
  scheduleTypes: string[];

  items: any[];

  recipes: Recipe[];
  schedule: Schedule = new Schedule();
  formSubmitted: boolean = false;

  constructor(formBuilder: FormBuilder, private scheduleService: ScheduleService,
    private recipeService: RecipeService, router: Router, private restaurantService: RestaurantService, private preparedFoodService: PreparedFoodService) {
    super(router, formBuilder);
  }

  ngOnInit() {
    this.buildFormforCreate();

    this.scheduleService.getAllMealTypes().subscribe(data => {
      this.mealTypes = data;
    })

    this.scheduleService.getAllScheduleTypes().subscribe(data => {
      this.scheduleTypes = data;
    })
  }

  onScheduleTypeChange(event) {
    this.items = [];
    if (event.target.value == this.scheduleTypes.find(element => element.toLowerCase() == "restaurant")) {
      this.restaurantService.findAll().subscribe(data => {
        this.items = data;
      })
    } else if (event.target.value == this.scheduleTypes.find(element => element.toLowerCase() == "recipe")) {
      this.recipeService.findAll().subscribe(data => {
        this.items = data;
      })
    } else if (event.target.value == this.scheduleTypes.find(element => element.toLowerCase() == "prepared food")) {
      this.preparedFoodService.findAll().subscribe(data => {
        this.items = data;
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
      this.schedule.mealType = this.form.get("mealType").value;
      this.schedule.scheduleType = this.form.get("scheduleType").value;
      this.schedule.scheduledTypeId = this.form.get("scheduleTypeId").value;
      this.scheduleService.add(this.schedule).subscribe(data => {
        this.router.navigate(['', this.date.getTime()]);
      })
    }
  }

  buildFormforEdit(id: number) {
    throw new Error("Method not implemented.");
  }

  buildFormforCreate() {
    this.form = this.formBuilder.group({
      epochDay: ['', Validators.required],
      mealType: ['', Validators.required],
      scheduleType: ['', Validators.required],
      scheduleTypeId: ['', Validators.required]
    });
  }
}
