import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FormComponent } from 'app/delectable/shared/component/form/base-form/form-component';
import { FileHandlerService } from 'app/delectable/shared/service/file-handler.service';
import { RestaurantService } from '../../service/restaurant.service';

@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent extends FormComponent implements OnInit {

  constructor(formBuilder: FormBuilder, private activatedroute: ActivatedRoute,
    private restaurantService: RestaurantService, router: Router, private fileHandlerService: FileHandlerService) {
    super(router, formBuilder);
  }

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      if (params.has('id')) {
        this.edit = true;
        this.id = Number.parseInt(params.get('id'));
        this.buildFormforEdit(this.id);
      } else {
        this.edit = false;
        this.buildFormforCreate()
      }
    })
  }

  buildFormforEdit(id: number) {
    this.restaurantService.getById(id).subscribe(restaurant => {
      this.getFormGroupComponent('element').addControl('name', new FormControl(restaurant.name, Validators.required));
      this.getFormGroupComponent('element').addControl('address', new FormControl(restaurant.address, Validators.required));
      this.getFormGroupComponent('element').addControl('addressNumber', new FormControl(restaurant.addressNumber));
      this.getFormGroupComponent('element').addControl('city', new FormControl(restaurant.city, Validators.required));
      this.getFormGroupComponent('element').addControl('state', new FormControl(restaurant.state, Validators.required));
      this.getFormGroupComponent('element').addControl('postalCode', new FormControl(restaurant.postalCode, Validators.required));
      this.getFormGroupComponent('element').addControl('phoneNumber', new FormControl(restaurant.phoneNumber));
      this.getFormGroupComponent('element').addControl('website', new FormControl(restaurant.website));
      this.getFormGroupComponent('element').addControl('carryOut', new FormControl(restaurant.carryOut));
      this.getFormGroupComponent('element').addControl('delivery', new FormControl(restaurant.delivery));
      this.getFormGroupComponent('element').addControl('glutenFreeOptions', new FormControl(restaurant.glutenFreeOptions));
    })
  }

  buildFormforCreate() {
    this.getFormGroupComponent('element').addControl('name', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('address', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('addressNumber', new FormControl(''));
    this.getFormGroupComponent('element').addControl('city', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('state', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('postalCode', new FormControl('', Validators.required));
    this.getFormGroupComponent('element').addControl('phoneNumber', new FormControl(''));
    this.getFormGroupComponent('element').addControl('website', new FormControl(''));
    this.getFormGroupComponent('element').addControl('carryOut', new FormControl());
    this.getFormGroupComponent('element').addControl('delivery', new FormControl());
    this.getFormGroupComponent('element').addControl('glutenFreeOptions', new FormControl());
  }

  submitForm() {
    this.formSubmitted = true;
    console.log(this.form.invalid);
    if (this.form.invalid) {
      return;
    } else {
      if (this.edit) {
        this.restaurantService.update(this.form.get('element').value, this.id).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      } else {
        this.restaurantService.create(this.form.get('element').value).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      }
    }
  }
}
