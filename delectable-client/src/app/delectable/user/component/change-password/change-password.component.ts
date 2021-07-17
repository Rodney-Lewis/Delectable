import { Component, OnInit } from '@angular/core';
import { AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators } from '@angular/forms';
import { UserAccountService } from '../../service/user.service';

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  constructor(private userService: UserAccountService) { }

  form: FormGroup;
  formSubmitted = false;
  isSuccessful = false;
  isFailed = false;
  message = '';

  ngOnInit(): void {
    this.form = new FormGroup({});
    this.form.addControl('oldPassword', new FormControl(null, [Validators.required]));
    this.form.addControl('newPassword', new FormControl(null, [Validators.required, Validators.minLength(6)]));
    this.form.addControl('confirmedPassword', new FormControl(null, [Validators.required, Validators.minLength(6)]));
    this.form.setValidators(this.passwordMatchValidator);
  }

  passwordMatchValidator: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    const confirmedPassword = control.get('confirmedPassword');
    const newPassword = control.get('newPassword');
    return newPassword && confirmedPassword && newPassword.value === confirmedPassword.value ? { passwordMatch: true } : null;
  };

  onSubmit() {
    this.formSubmitted = true;
    this.userService.updateCurrentUserPassword(this.form.value).subscribe(
      data => {
        this.message = data.message;
        this.isSuccessful = true;
        this.isFailed = false;
        this.form.reset();
      },
      err => {
        this.message = err.error;
        this.isSuccessful = false;
        this.isFailed = true;
    });
  }

  get oldPassword() { return this.form.get('oldPassword'); }
  get newPassword() { return this.form.get('newPassword'); }
  get confirmedPassword() { return this.form.get('confirmedPassword'); }

}
