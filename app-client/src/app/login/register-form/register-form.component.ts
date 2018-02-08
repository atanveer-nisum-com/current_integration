import { LoginService } from './../../service/login.service';
import { CART_REMOVE_ITEM } from './../../state-management/action/cart.action';
import { Store } from '@ngrx/store';
import { AppState } from './../../state-management/state/app.state';
import { SET_USER_LOGIN } from './../../state-management/action/user.action';
import { Router, NavigationEnd } from '@angular/router';
import { Location } from '@angular/common';
import { EMAIL_REGEX } from './../../shared/utility';
import { Component, OnInit } from '@angular/core';
import { SignupService } from './../../service/signup.service';
import { FormBuilder, FormGroup, FormControl, Validators } from '@angular/forms';
import { PasswordValidation } from './equal-validator.directive';
import swal from 'sweetalert2';
import { User } from '../../models/user';
import { Address } from '../../models/address';
import { CountryService } from '../../service/country.service';
import { Observable } from 'rxjs/Observable';
import { Country } from '../../models/country';
import { CountryState } from '../../models/state';
import { CountryStatesService } from '../../service/country-states.service';


@Component({
  selector: 'register-form',
  styleUrls: ['./register-form.component.css'],
  templateUrl: './register-form.component.html',
  providers: [SignupService, LoginService, CountryService, CountryStatesService]
})
/**
 *Class 'RegisterFormComponent' to Register the user
 */
export class RegisterFormComponent implements OnInit {
  registerFormTitle = 'Create a profile';
  cities = ['Really Smart', 'Super Flexible', 'Weather Changer'];
  countries$: Observable<Country>;
  // states = [{state: 'ALASKA', code: 'AK'}, {state: 'ALABAMA', code: 'AL'}, {
  //   state: 'ARKANSAS',
  //   code: 'AR'
  // }, {state: 'ARIZONA', code: 'AZ'}];
  userForm: FormGroup;
  states$: Observable<CountryState>;
  location: Location;
  previousUrl: string;
  router: Router;
  usCountryId = 230;

  /**
   *
   * @param fb
   * @param signupService
   */
  constructor(private fb: FormBuilder, private signupService: SignupService,
    private loginService: LoginService, private countryService: CountryService,
    private countryStatesService: CountryStatesService, _router: Router, _location: Location,
    private store: Store<AppState>) {
    this.countries$ = this.countryService.getCountriesList();
    this.states$ = this.countryStatesService.getStates(this.usCountryId);
    this.location = _location;
    this.router = _router;

  }

  ngOnInit(): void {
    this.createForm();
  }

  /**
   * method 'createForm' to create form fields and apply validations
   */
  createForm(): void {
    this.userForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailAddress: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
      cPassword: ['', Validators.required],
      addressLine1: ['', Validators.required],
      addressLine2: [''],
      states: [''],
      city: '',
      postalCode: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      country: ['', Validators.required]
    }, { validator: PasswordValidation.MatchPassword });

    this.userForm.valueChanges.debounceTime(1000)
      .subscribe(data => this.onValueChanged(data));

    this.onValueChanged();
  }

  /**
   * On key value changed event
   * @param data
   */
  onValueChanged(data?: any) {
    if (!this.userForm) {
      return;
    }
    const form = this.userForm;


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

  /**
   * displaying form error fields while validation
   */
  // tslint:disable-next-line:member-ordering
  formErrors = {
    'firstName': '',
    'lastName': '',
    'emailAddress': '',
    'password': '',
    'cPassword': '',
    'address': '',
    'postalCode': '',
    'phoneNumber': ''
  };

  /**
   * displaying form error messages while validation
   */
  // tslint:disable-next-line:member-ordering
  validationMessages = {
    'firstName': {
      'required': 'First Name is required.',
    },
    'lastName': {
      'required': 'Last Name is required.',
    },
    'emailAddress': {
      'required': 'Email address is required.',
      'pattern': 'Invalid email address'
    },
    'password': {
      'required': 'Must be 6-25 characters long.',
      'minlength': 'Password should be greater than 6 characters',
      'maxlength': 'Password exceeded than 25 characters',
    },
    'cPassword': {
      'required': 'Password Mismatch.',
    },
    'address': {
      'required': 'Address is required.',
    },
    'postalCode': {
      'required': 'Zip Code is required.',
    },
    'phoneNumber': {
      'required': 'Phone Number is required.',
    }
  };

  /**
   * 'onSubmit' method call signup api and return response
   */
  onSubmit() {
    const formData = this.userForm.value;

    let countryState: CountryState = null;

    if (formData.country.toString() === this.usCountryId.toString()) {
      countryState = {
        id: formData.states
      };
    }

    /**
     *This model will be send to server with user signup data.
     */
    const userModel: User = {
      firstName: formData.firstName,
      lastName: formData.lastName,
      emailAddress: formData.emailAddress,
      password: formData.password,
      addresses: [{
        addressLine1: formData.addressLine1,
        addressLine2: formData.addressLine2,
        addressType: 1,
        phoneNumber: formData.phoneNumber,
        city: formData.city,
        stateBean: countryState,
        zipcode: formData.postalCode,
        countryBean: {
          id: formData.country,
        }
      }]
    };

    if (this.userForm.valid) {
      this.signupService.doSingnup(userModel).subscribe(data => {
        let user: User = data;
        user.password = formData.password;
        user.rememberMe = false;
        this.loginService.doLogin(user).subscribe(res => {
          if (res.userId) {
            localStorage.setItem('user', res.userId);
            localStorage.setItem('userToken', res.token);
            localStorage.setItem('isLoged', 'true');
            this.store.dispatch({ type: SET_USER_LOGIN, payload: true });
            this.store.dispatch({ type: CART_REMOVE_ITEM, payload: res.cartCount });
          }
        });
        swal('Success', 'Successfully Registered.', 'success');
        if (location.hostname === 'localhost') {
          this.location.back();
        }
      }, err => {
        const errorMsg = JSON.parse(err._body || '{error: Request Cant Be Completed Because Of Some Problem!}').error;
        swal('', errorMsg,
          'error'
        );
      });
    } else {
      this.userForm.updateValueAndValidity();
      if (this.userForm.invalid) {
        Object.keys(this.userForm.controls).forEach(key => {
          this.userForm.get(key).markAsDirty();
        });
        return;
      }
    }
  }
}
