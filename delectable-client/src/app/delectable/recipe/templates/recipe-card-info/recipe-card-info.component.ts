import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-recipe-card-info',
  templateUrl: './recipe-card-info.component.html',
  styleUrls: ['./recipe-card-info.component.css']
})
export class RecipeCardInfoComponent {

  constructor() { }

  @Input() item: any;
  @Input() detailItemRoute: string;
}
