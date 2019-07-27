import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-recipe-form',
  templateUrl: './recipe-form.component.html',
  styleUrls: ['./recipe-form.component.css']
})
export class RecipeFormComponent implements OnInit {

  maxHours:number;
  maxMinutes:number;
  maxSeconds:number;
  maxHourRange:number[];
  maxMinuteRange:number[];
  maxSecondRange:number[];

  constructor() { 
    this.maxHours = 24;
    this.maxMinutes = 59;
    this.maxSeconds = 59;
    this.maxHourRange = [];
    this.maxMinuteRange = [];
    this.maxSecondRange = [];
  }

  ngOnInit() {
    for(var index = 0; index <= this.maxHours; index++) {
      this.maxHourRange.push(index);
    }

    for(var index = 0; index <= this.maxMinutes; index++) {
      this.maxMinuteRange.push(index);
    }

    for(var index = 0; index <= this.maxSeconds; index++) {
      this.maxSecondRange.push(index);
    }
    console.log(this.maxSecondRange);
  }

}
