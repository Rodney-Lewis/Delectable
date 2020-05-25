import { Component, OnInit } from '@angular/core';
import { Pantry } from 'app/delectable/pantry/pantry';
import { PantryService } from 'app/delectable/pantry/pantry.service';

@Component({
  selector: 'app-pantry-list',
  templateUrl: './pantry-list.component.html',
  styleUrls: ['./pantry-list.component.css']
})
export class PantryListComponent implements OnInit {

  pantryItems: Pantry[];
  constructor(private pantryService: PantryService) { }

  ngOnInit() {
    this.pantryService.findAll().subscribe(data => {
      this.pantryItems = data;
    })
  }
}
