import { Component, Input } from '@angular/core';
import '../../../shared/component/carddeck/carddeck.component.css'

@Component({
  selector: 'app-recipe-card-info',
  templateUrl: './recipe-card-info.component.html',
  styleUrls: ['./recipe-card-info.component.css', '../../../shared/component/carddeck/carddeck.component.css']
})
export class RecipeCardInfoComponent {

  constructor() { }

  @Input() item: any;
  @Input() detailItemRoute: string;
}
