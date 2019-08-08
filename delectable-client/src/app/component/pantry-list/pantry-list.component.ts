import { Component, OnInit } from '@angular/core';
import { Pantry } from '../../model/pantry';
import { PantryService } from '../../service/pantry.service';

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
      console.log(data);
    })
  }
}
