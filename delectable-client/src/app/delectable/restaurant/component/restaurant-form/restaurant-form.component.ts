import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormArray, Validators, FormControl, FormGroup, Form } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { RestaurantService } from '../../service/restaurant.service';
import { FormWithImageComponent } from 'app/delectable/_component/form-with-image-component';
import { FileHandlerService } from 'app/delectable/_service/imagehandler/file-handler.service';


@Component({
  selector: 'app-restaurant-form',
  templateUrl: './restaurant-form.component.html',
  styleUrls: ['./restaurant-form.component.css']
})
export class RestaurantFormComponent extends FormWithImageComponent implements OnInit {

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
    this.restaurantService.findById(id).subscribe(restaurant => {
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
      this.getFormGroupComponent('element').addControl('imageSource', new FormControl(restaurant.imageSource));
      this.previewImage = this.fileHandlerService.getNamedImageUrl(restaurant.imageSource);

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
    this.getFormGroupComponent('element').addControl('imageSource', new FormControl(''));
  }

  submitForm() {
    this.formSubmitted = true;
    console.log(this.form.invalid);
    if (this.form.invalid) {
      return;
    } else {
      if (this.getFormComponent("image.imageSourceFile").value != "") {
        const imageFormData = new FormData();
        imageFormData.append('imageMultipartFile', this.getFormComponent('image.imageMultipartFile').value);
        this.fileHandlerService.add(imageFormData).subscribe();
      }
      if (this.edit) {
        this.restaurantService.update(this.form.get('element').value, this.id).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      } else {
        this.restaurantService.add(this.form.get('element').value).subscribe(() => {
          this.router.navigate(['/restaurant/list']);
        });
      }
    }
  }
}
