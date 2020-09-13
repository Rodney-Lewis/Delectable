import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { PantryService } from 'app/delectable/pantry/service/pantry.service';
import { PantryItem } from 'app/delectable/pantry/model/pantry';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pantry-form',
  templateUrl: './pantry-form.component.html',
  styleUrls: ['./pantry-form.component.css']
})
export class PantryFormComponent implements OnInit {

  pantryForm = new FormGroup({
    name: new FormControl('')
  });
  pantryItem: PantryItem;

  constructor(private formBuilder: FormBuilder, private pantryService: PantryService, 
    private router: Router) {
  }

  ngOnInit() {
    this.pantryForm = this. formBuilder.group({
      name: new FormControl('')
    })
  }

  onSubmit() {
    this.pantryService.add(this.pantryForm.value).subscribe(data => {
      this.router.navigate(['/pantry/list']);
    });
  }
}
