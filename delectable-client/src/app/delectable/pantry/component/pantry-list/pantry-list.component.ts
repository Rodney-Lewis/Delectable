import { Component, OnInit } from '@angular/core';
import { PantryItem } from 'app/delectable/pantry/model/pantry';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';

@Component({
  selector: 'app-pantry-list',
  templateUrl: './pantry-list.component.html',
  styleUrls: ['./pantry-list.component.css']
})
export class PantryListComponent implements OnInit {

  pantryItems: PantryItem[];
  constructor(private pantryService: PantryService) { }

  ngOnInit() {
    this.pantryService.findAll().subscribe(pantryItems => {
      this.pantryItems = pantryItems;
    })
  }
}
