import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormControl, FormGroup, Form } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../../restaurant.service';


@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent implements OnInit {

  restaurantForm: FormGroup;
  editRestaurant: boolean;
  formSubmitted: boolean;
  id: Number;

  constructor(private formBuilder: FormBuilder, private activatedroute: ActivatedRoute,
    private restaurantService: RestaurantService, private router: Router) {

  }

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      if (params.get('id') != null) {
        this.id = Number(params.get('id'));
        this.editRestaurant = true;
        this.restaurantService.findById(Number.parseInt(params.get('id'))).subscribe(data => {
          this.restaurantForm = this.formBuilder.group({
            restaurant: this.formBuilder.group({
              name: [data.name, Validators.required]
            })
          })
        })
      } else {
        this.editRestaurant = false;
        this.restaurantForm = this.formBuilder.group({
          restaurant: this.formBuilder.group({
            name: ['', Validators.required]
          })
        })
      }
    })
  }

  onSubmit() {
    this.formSubmitted = true;
    if (this.restaurantForm.invalid) {
      return;
    } else {
      if (this.editRestaurant) {
        this.restaurantService.update(this.restaurantForm.get('restaurant').value, this.id).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      } else {
        this.restaurantService.add(this.restaurantForm.get('restaurant').value).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      }
    }
  }

  getFormComponent(component: string) {
    return this.restaurantForm.get(component);
  }

  getFormArrayComponent(component: string) {
    return this.restaurantForm.get(component) as FormArray;
  }

  formFieldInvalid(component: string) {
    return this.getFormComponent(component).invalid && (this.getFormComponent(component).dirty || this.getFormComponent(component).touched || this.formSubmitted)
  }

  formFieldRequired(component: string) {
    if (this.formFieldInvalid(component))
      return this.getFormComponent(component).errors?.required;
  }

}
