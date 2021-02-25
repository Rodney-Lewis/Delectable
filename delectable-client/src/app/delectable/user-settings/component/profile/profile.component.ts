import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TokenStorageService } from '../../../login/user_auth/token-storage.service';
import { UserService } from '../../service/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class AccountComponent implements OnInit {

  constructor(private router: Router, private userService: UserService, private tokenStorageService: TokenStorageService) { }

  form: FormGroup;
  formSubmitted = false;
  isSuccessful = false;
  isFailed = false;
  message = '';

  ngOnInit(): void {
    this.form = new FormGroup({});
    this.form.addControl('username', new FormControl(null, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]));
    this.form.addControl('email', new FormControl(null, [Validators.email]));
    this.form.addControl('role', new FormControl({ value: null, disabled: true }));
    this.userService.getCurrentUserDetails().subscribe(user => {
      this.form.setValue({ username: user.username, email: user.email, role: user.authorities[0].authority })
    })
  }

  onSubmit() {
    this.formSubmitted = true;
    this.userService.updateCurrentUserDetails(this.form.value).subscribe(
      data => {
        this.message = data.message;
        this.isSuccessful = true;
        this.isFailed = false;
      },
      err => {
        this.message = err.error;
        this.isSuccessful = false;
        this.isFailed = true;
    });
  }

  signout(): void {
    this.tokenStorageService.clearSession();
  }

  get username() { return this.form.get('username'); }
  get email() { return this.form.get('email'); }

}
