import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { FormComponent } from 'app/delectable/shared/component/form/base-form/form-component';
import { UserManagementService } from '../../service/admin-service.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent extends FormComponent implements OnInit {


  constructor(private formbuilder: FormBuilder, router: Router, private adminService: UserManagementService, private activatedroute: ActivatedRoute) {
    super(router, formbuilder);
  }

  roles: any;

  ngOnInit(): void {
    this.activatedroute.paramMap.subscribe(params => {
      this.id = Number(params.get("id"));
      this.buildFormforEdit(this.id);
      this.adminService.getRoles().subscribe(roles => {
        this.roles = roles;
      })
    })
  }

  buildFormforEdit(id: number) {
    this.adminService.getById(id).subscribe(user => {
      let formGroup = this.getFormGroupComponent('element');
      formGroup.addControl('username', new FormControl(user.username));
      formGroup.addControl('email', new FormControl(user.email));
      formGroup.addControl('role', new FormControl(user.role));
    })
  }

  buildFormforCreate() {
    throw new Error('Method not implemented.');
  }
  
  submitForm() {
    this.adminService.updateUser(this.id, this.getFormComponent("element").value).subscribe(() => {
      this.router.navigate(['/admin']);
    });
  }

}
