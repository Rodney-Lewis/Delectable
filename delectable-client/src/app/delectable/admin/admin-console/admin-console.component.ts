import { Component, OnInit } from '@angular/core';
import { FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { FormComponent } from 'app/delectable/_component/form/base-form/form-component';
import { AdminService } from '../admin-service.service';

@Component({
  selector: 'app-admin-console',
  templateUrl: './admin-console.component.html',
  styleUrls: ['./admin-console.component.css']
})
export class AdminConsoleComponent implements OnInit {

  constructor(private adminService: AdminService) {}

  responseBody: any;
  responseRoles: any;

  ngOnInit(): void {
    this.adminService.findAll().subscribe(fullResponse => {
      this.responseBody = fullResponse.body.content
    })
  }

}
