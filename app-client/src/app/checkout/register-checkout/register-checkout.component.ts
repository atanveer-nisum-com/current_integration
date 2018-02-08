import { AddressBookService } from './../../service/address-book.service';
import { Address } from './../../models/address';
import { EMAIL_REGEX } from './../../shared/utility';
import { LoginService } from './../../service/login.service';
import { Observable } from 'rxjs/Rx';
import { User } from './../../models/user';
import { PasswordValidation } from './../../login/register-form/equal-validator.directive';
import { CountryStatesService } from './../../service/country-states.service';
import { SignupService } from './../../service/signup.service';
import { CountryService } from './../../service/country.service';
import { AppState } from './../../state-management/state/app.state';
import { Store } from '@ngrx/store';
import { CountryState } from './../../models/state';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Country } from './../../models/country';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { PostalCodeService } from '../../service/postalcode.service';
import { IS_SHIPPING_FORM_VALID } from '../../state-management/action/form.action';
import swal from 'sweetalert2';

@Component({
  moduleId: module.id,
  selector: 'app-register-checkout',
  templateUrl: 'register-checkout.component.html',
  styleUrls: ['register-checkout.component.css'],
  providers: [SignupService, CountryService,
    CountryStatesService, LoginService,
    PostalCodeService, AddressBookService]
})
export class RegisterCheckoutComponent implements OnInit, OnDestroy {

  countries$: Observable<Country>;
  user: User;
  addresses: Address[];
  defaultAddress: Address;
  userForm: FormGroup;
  shipToForm: FormGroup;
  states$: Observable<CountryState>;
  usCountryId = 230;
  userId;
  exceedLimit;

  isGuest: number;

  /**
   *
   * @param fb
   * @param signupService
   */
  constructor(private store: Store<AppState>, private fb: FormBuilder, private loginService: LoginService,
    private signupService: SignupService, private countryService: CountryService,
    private countryStatesService: CountryStatesService, private postalCodeService: PostalCodeService,
    private addressBookService: AddressBookService) {
    this.userId = +localStorage.getItem('user');

  }

  ngOnInit(): void {
    const user: number = +localStorage.getItem('user');
    if (user && user > 0) {
      this.isGuest = 0;
    } else {
      this.isGuest = 1;
    }

    if (localStorage.getItem('checkoutInfo')) {
      localStorage.removeItem('checkoutInfo');
    }

    console.log('isGuest: ' + this.isGuest);
    this.createForm();
    // this.getDefaultAddress();
    this.createShipToForm();

    this.authorizeUserToken();
    this.countries$ = this.countryService.getCountriesList();

    this.getUserAddress(this.userId);
    this.states$ = this.countryStatesService.getStates(this.usCountryId);
  }

  fillShippingAddress(event) {

    let shipToForm = this.shipToForm;
    if (event.target.value == 'addNew' && this.exceedLimit >= 10) {
      swal('', 'Your address limit has been exceed',
        'error'
      );
      shipToForm.disable();
    } else if (event.target.value !== 'addNew' && event.target.value !== 'null') {
      this.createShipToForm(JSON.parse(event.target.value));
      shipToForm = this.shipToForm;
      this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: true } });
    } else {
      this.createShipToForm();
      shipToForm = this.shipToForm;
      setTimeout(function () {
        // tslint:disable-next-line:forin
        for (const val in shipToForm.value) {
          const control = shipToForm.get(val);
          control.enable();
          // control.updateValueAndValidity();
        }
      }, 100);
      this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
    }
  }

  /**
   * method 'createForm' to create form fields and apply validations
   */
  createForm(): void {
    this.userForm = this.fb.group({
      addressId: this.user !== undefined ? this.user.addresses[0].id : -1,
      firstName: [(this.user !== undefined) ? this.user.firstName : '', Validators.required],
      lastName: [(this.user !== undefined) ? this.user.lastName : '', Validators.required],
      emailAddress: [(this.user !== undefined) ? this.user.emailAddress : '',
      [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
      cPassword: ['', Validators.required],
      addressLine1: [(this.user !== undefined) ? this.user.addresses[0].addressLine1 : '', Validators.required],
      addressLine2: [(this.user !== undefined) ? this.user.addresses[0].addressLine2 : ''],
      states: (this.user !== undefined && this.user.addresses[0].stateBean) ? this.user.addresses[0].stateBean.id : '',
      city: (this.user !== undefined) ? this.user.addresses[0].city : '',
      isGuest: this.isGuest,
      postalCode: [(this.user !== undefined) ? this.user.addresses[0].zipcode : '', Validators.required],
      phoneNumber: [(this.user !== undefined) ? this.user.addresses[0].phoneNumber : '', Validators.required],
      country: [(this.user !== undefined) ? this.user.addresses[0].countryBean.id : '', Validators.required]
    }, { validator: PasswordValidation.MatchPassword });

    this.userForm.valueChanges.debounceTime(1000)
      .subscribe(data => this.onValueChanged(data));

    // this.onValueChanged();

  }

  /**
   * method 'createShipToForm' create form fields for shipping address for checkout and apply validations
   * @param address
   */

  createShipToForm(address?: Address): void {
    this.shipToForm = this.fb.group({
      addressId: address !== undefined ? address.id : -1,
      addressLine1: [(address !== undefined) ? address.addressLine1 : '', Validators.required],
      addressLine2: [(address !== undefined) ? address.addressLine2 : ''],
      states: (address !== undefined && address.stateBean) ? address.stateBean.id : '',
      city: (address !== undefined) ? address.city : '',
      shipPostalCode: [(address !== undefined) ? address.zipcode : '', Validators.required],
      phoneNumber: [(address !== undefined) ? address.phoneNumber : '', Validators.required],
      country: [(address !== undefined) ? address.countryBean.id : '', Validators.required]
    });
    if (localStorage.getItem('user') && this.addresses != null)
      this.shipToForm.disable();
    this.shipToForm.valueChanges.debounceTime(1000)
      .subscribe(data => this.onShipToValueChanged(data));
    // this.shipToForm.updateValueAndValidity();

    if(address != null) {
      this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: true } });
    }

  }

  /**
   * On key value changed event
   * @param data
   */

  onShipToValueChanged(d?: any) {
    if (!this.shipToForm) {
      return;
    }
    const form = this.shipToForm;

    this.doValidateZipCode(form).debounceTime(800).subscribe(data => {
      const city = this.shipToForm.get('city');
      const state = this.shipToForm.get('states');
      const postalCode = this.shipToForm.get('shipPostalCode');
      const country = this.shipToForm.get('country');

      if (data.error) {
        postalCode.setErrors({ wrongZip: true });
        postalCode.markAsDirty();
        this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
      } else {
        if (country.value == this.usCountryId && (city.value == '' || postalCode.value == '' || state.value == '')) {
          this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
        } else if (country.value != this.usCountryId && (city.value == '' || postalCode.value == '')) {
          this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
        } else {
          this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: true } });
        }
      }

      for (const field in this.formErrors) {
        // clear previous error message (if any)
        this.formErrors[field] = '';
        const control = this.shipToForm.get(field);

        if (control && control.dirty && !control.valid) {
          const messages = this.validationMessages[field];
          for (const key in control.errors) {
            if (country.value != this.usCountryId) {
              this.formErrors['stateBean'] = '';
              continue;
            }
            this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
            this.formErrors[field] += messages[key] + ' ';
          }
        }
      }
      console.log('onShipToValueChanged()');
    });
  }

  doValidateZipCode(form): Observable<any> {

    let dumyObs: Observable<any> = Observable.of({});

    const city = form.get('city');
    const state = form.get('states');
    const postalCode = form.get('shipPostalCode');
    const country = form.get('country');

    // If Selected Country Is U.S
    if (country.value == this.usCountryId) {
      const strCity = city.value || '0';
      const strStateId = state.value || '0';
      const strPostalCode = postalCode.value || '0';
      const strCountryId = country.value;

      if (strPostalCode.length > 3 && ((postalCode.touched) || (city.touched) ||
        (state.touched) || (country.touched))) {
        dumyObs = this.postalCodeService.isPostalCodeValid(strPostalCode, strCity, strStateId, strCountryId).map(response => response.json());
      }
    }
    console.log('doValidateZipCode()');
    return dumyObs;
  }

  stateValue(stateBean: CountryState) {
    if (stateBean !== null) {
      if (typeof stateBean.id !== 'string') {
        return stateBean.id;
      }
    }
  }

  countryValue(countryBean: Country) {
    if (countryBean !== null) {
      if (typeof countryBean.id !== 'string') {
        return countryBean.id;
      }
    }
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

    this.recheckFieldsForValidationErrors(form);
  }

  /**
   * this function fill shipTo Address same as billing address
   */
  sameAddress(event) {
    const stateBean: CountryState = {
      id: this.userForm.value.states ? this.userForm.value.states : ''
    };

    const countryBean: Country = {
      id: this.userForm.value.country ? this.userForm.value.country : ''
    };

    const billAddress: Address = {
      addressLine1: this.userForm.value.addressLine1,
      addressLine2: this.userForm.value.addressLine2,
      city: this.userForm.value.city,
      addressType: 1,
      stateBean: stateBean,
      zipcode: this.userForm.value.postalCode,
      countryBean: countryBean,
      phoneNumber: this.userForm.value.phoneNumber
    };
    let shipToForm;
    if (event.target.checked) {
      this.createShipToForm(billAddress);
      shipToForm = this.shipToForm;
      setTimeout(function () {
        // tslint:disable-next-line:forin
        for (const val in shipToForm.value) {
          if (shipToForm.get(val).value !== '') {
            const control = shipToForm.get(val);
            control.disable();
            control.updateValueAndValidity();
          }
        }
      }, 100);
    } else {
      this.createShipToForm();
      // this.setFieldsDisable(this.shipToForm);
      shipToForm = this.shipToForm;
      setTimeout(function () {
        // tslint:disable-next-line:forin
        for (const val in shipToForm.value) {
          const control = shipToForm.get(val);
          control.enable();
          control.updateValueAndValidity();
        }
      }, 100);
    }

    this.recheckFieldsForValidationErrors(this.shipToForm);
    this.doValidateZipCode(this.shipToForm);
  }

  recheckFieldsForValidationErrors(form) {
    for (const field in this.formErrors) {
      // clear previous error message (if any)
      this.formErrors[field] = '';
      const control = form.get(field);

      if (control != null && (control.dirty && !control.valid)) {
        const messages = this.validationMessages[field];
        // tslint:disable-next-line:forin
        for (const key in control.errors) {
          this.formErrors[field] += messages[key] + ' ';
          this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
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
    'emailAddressShip': '',
    'password': '',
    'cPassword': '',
    'address': '',
    'postalCode': '',
    'shipPostalCode': '',
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
      'minlength': 'Password should be greater than 6 digits',
      'maxlength': 'Password exceeded than 25 digits',
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
    'shipPostalCode': {
      'required': 'Zip Code is required.',
      'wrongZip': 'Zip Code, City, State does not match.'
    },
    'phoneNumber': {
      'required': 'Phone Number is required.',
    }
  };

  getUserAddress(userId): any {
    if (this.isGuest == 0) {
      this.addressBookService.getAllShippingAddresses(userId).subscribe(data => {
        this.addresses = data.content;
        this.exceedLimit = data.content.length;
        this.createShipToForm(this.addresses[0]);
      });
    }
  }

  authorizeUserToken(): any {
    if (localStorage.getItem('userToken') !== null && localStorage.getItem('user') !== null) {
      // tslint:disable-next-line:radix
      const userAuth = { 'token': localStorage.getItem('userToken'), 'userId': parseInt(localStorage.getItem('user')) };

      this.loginService.getUserObject(userAuth).subscribe((res: User) => {
        this.user = res;
        this.createForm();
      });
    }
  }

  ngOnDestroy(): void {

    const data = {
      'userform': this.userForm.value,
      'shipForm': this.shipToForm.value,
    };
    console.log('destroy: ' + JSON.stringify(this.shipToForm.value));
    localStorage.setItem('checkoutInfo', JSON.stringify(data));

    // if (this.userForm !== null && this.userForm !== undefined) {
    //   // const isGuest = this.userForm.value.isGuest;
    //   // if (isGuest == 1) {
    //   this.store.dispatch({
    //     type: 'guestState',
    //     payload: {userForm: this.userForm.value, shipToForm: this.shipToForm.value}
    //   });
    //   // }
    // }
  }
}