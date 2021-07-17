import { Component, Input, ContentChild, TemplateRef } from '@angular/core';

@Component({
  selector: 'app-carddeck',
  templateUrl: './carddeck.component.html',
  styleUrls: ['./carddeck.component.css']
})
export class CarddeckComponent {

  @Input() items: any[] = [];
  @ContentChild('item', { static: false }) 
  template: TemplateRef<any>;

}
