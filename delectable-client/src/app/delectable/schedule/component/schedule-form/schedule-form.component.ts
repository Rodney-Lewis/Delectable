import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, Validators } from '@angular/forms';
import { ScheduleService } from 'app/delectable/schedule/schedule.service';
import { RecipeService } from 'app/delectable/recipe/recipe.service';
import { Recipe } from 'app/delectable/recipe/recipe';
import { Schedule } from 'app/delectable/schedule/schedule';

@Component({
  selector: 'app-schedule-form',
  templateUrl: './schedule-form.component.html',
  styleUrls: ['./schedule-form.component.css']
})
export class ScheduleFormComponent implements OnInit {
  epochString: string;
  epochStrings: string[];
  date: Date;
  mealTypes: string[];

  recipes: Recipe[];
  schedule: Schedule = new Schedule();
  formSubmitted: boolean = false;

  scheduleForm = this.formBuilder.group({
    epochDay: ['', Validators.required],
    mealType: ['', Validators.required],
    recipe: ['', Validators.required]
  })
  constructor(private formBuilder: FormBuilder, private scheduleService: ScheduleService,
    private recipeService: RecipeService, private router: Router) { }

  ngOnInit() {
    this.recipeService.findAll().subscribe(data => {
      this.recipes = data;
    })

    this.scheduleService.getAllMealTypes().subscribe(data => {
      this.mealTypes = data;
    })
  }

  onSubmit() {
    this.formSubmitted = true;
    if (this.scheduleForm.invalid) {
      return;
    } else {
      this.epochString = this.scheduleForm.get("epochDay").value;
      this.epochStrings = this.epochString.split("-");
      this.date = new Date(parseInt(this.epochStrings[0]), parseInt(this.epochStrings[1]) - 1, parseInt(this.epochStrings[2]));
      this.schedule.epoch = this.date.getTime();
      this.schedule.mealType = this.scheduleForm.get("mealType").value;
      this.schedule.recipe = new Recipe();
      this.schedule.recipe.id = this.scheduleForm.get("recipe").value;
      this.scheduleService.add(this.schedule).subscribe(data => {
        this.router.navigate(['', this.date.getTime()]);
      })
    }
  }

  getFormComponent(component: string) {
    return this.scheduleForm.get(component);
  }

  formFieldInvalid(component: string) {
    return this.getFormComponent(component).invalid && (this.getFormComponent(component).dirty || this.getFormComponent(component).touched)
  }

  formFieldRequired(component: string) {
    if (this.formFieldInvalid(component))
      return this.getFormComponent(component).errors?.required;
  }

}
