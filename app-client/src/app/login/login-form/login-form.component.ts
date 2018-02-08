import { CART_REMOVE_ITEM } from './../../state-management/action/cart.action';
import { SET_MESSAGE } from './../../state-management/action/forgot-password.action';
import { ForgotPasswordState } from './../../state-management/state/forgot-password.state';
import { SET_USER_LOGIN } from './../../state-management/action/user.action';
import { EMAIL_REGEX } from './../../shared/utility';
import swal from 'sweetalert2';
import { LoginService } from './../../service/login.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Store } from '@ngrx/store';
import { AppState } from './../../state-management/state/app.state';

@Component({
  selector: 'login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css'],
  providers: [LoginService]
})
export class LoginFormComponent implements OnInit, OnDestroy {

  loginForm: FormGroup;
  responseMessage;
  // router: Router;
  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router, private store: Store<AppState>) {
  }

  createLoginForm() {

    this.getMessgaeByRedux();
    this.loginForm = this.fb.group({
      emailAddress: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
      rememberMe: [false]
    });
    this.loginForm.valueChanges.debounceTime(800).subscribe(data => this.onValueChanged(data));
    this.onValueChanged();
  }

  onValueChanged(data?: any) {
    if (!this.loginForm) { return; }
    const form = this.loginForm;

    // tslint:disable-next-line:forin
    for (const field in this.formErrors) {
      // clear previous error message (if any)
      this.formErrors[field] = '';
      const control = form.get(field);

      if (control && control.dirty && !control.valid) {
        const messages = this.validationMessages[field];
        // tslint:disable-next-line:forin
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + ' ';
        }
      }
    }
  }

  // tslint:disable-next-line:member-ordering
  formErrors = {
    'emailAddress': '',
    'password': ''
  };

  // tslint:disable-next-line:member-ordering
  validationMessages = {

    'emailAddress': {
      'required': 'Email address is required.',
      'pattern': 'Invalid email address',
    },
    'password': {
      'required': 'Must be 6-25 characters long.',
      'minlength': 'Password should be greater than 6 characters',
      'maxlength': 'Password exceeded than 25 characters',
    }

  };

  ngOnInit(): void {
    this.createLoginForm();
  }


  onSubmit() {
    if (this.loginForm.valid) {
      this.loginService.doLogin(this.loginForm.value).subscribe(data => {
        if (data.token) {
          localStorage.setItem('user', data.userId);
          localStorage.setItem('userToken', data.token);
          console.log('Old State   ' + localStorage.getItem('oldstate'));
          if (localStorage.getItem('oldstate') != null) {
            this.router.navigate(['/' + localStorage.getItem('oldstate')]);
            localStorage.setItem('oldstate', '');
          }
          if (localStorage.getItem('oldstate') == null) {
            this.router.navigate(['']);
          }
          this.store.dispatch({ type: SET_USER_LOGIN, payload: true });
          this.store.dispatch({ type: CART_REMOVE_ITEM, payload: data.cartCount });
        }
      }, err => {
        const errorMsg = JSON.parse(err._body || '{error: Request Cant Be Completed Because Of Some Problem!}').error;
        swal('Oops..', errorMsg,
          'error');
      });
    } else {
      this.loginForm.updateValueAndValidity();
      if (this.loginForm.invalid) { 
        Object.keys(this.loginForm.controls).forEach(key => {
          this.loginForm.get(key).markAsDirty();
        });
        return;
      }
    }
  }

  getMessgaeByRedux() {
    this.store.select('forgotPasswordState').subscribe((data: ForgotPasswordState) => {
      if (!data.message) {
        return;
      }
      this.responseMessage = data.message;
    });
  }

  ngOnDestroy(): void {
    this.store.dispatch({ type: SET_MESSAGE, payload: null });
  }
}
