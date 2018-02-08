import { EMAIL_REGEX } from './../../shared/utility';
import {Country} from './../../models/country';
import {Observable} from 'rxjs/Observable';
import {User} from './../../models/user';
import {CountryState} from './../../models/state';
import {PasswordValidation} from './../../login/register-form/equal-validator.directive';
import {CountryService} from './../../service/country.service';
import {FormBuilder, Validators, FormGroup} from '@angular/forms';
import {CountryStatesService} from './../../service/country-states.service';
import {SignupService} from './../../service/signup.service';
import {GuestState} from './../../state-management/state/guest-checkout-state';
import {AppState} from './../../state-management/state/app.state';
import {Store} from '@ngrx/store';
import {Component, OnDestroy, Output, AfterViewInit, OnInit} from '@angular/core';
import {PostalCodeService} from '../../service/postalcode.service';
import {IS_SHIPPING_FORM_VALID} from '../../state-management/action/form.action';

@Component({
  moduleId: module.id,
  selector: 'app-guest-checkout',
  templateUrl: 'guest-checkout.component.html',
  styleUrls: ['guest-checkout.component.css'],
  providers: [SignupService, CountryService, CountryStatesService, PostalCodeService]
})
export class GuestCheckoutComponent implements OnDestroy, OnInit {

  cities = ['Really Smart', 'Super Flexible', 'Weather Changer'];
  countries$: Observable<Country>;
  // states = [{state: 'ALASKA', code: 'AK'}, {state: 'ALABAMA', code: 'AL'}, {
  //   state: 'ARKANSAS',
  //   code: 'AR'
  // }, {state: 'ARIZONA', code: 'AZ'}];
  userForm: FormGroup;
  shipToForm: FormGroup;
  states$: Observable<CountryState>;

  usCountryId = 230;

  /**
   *
   * @param fb
   * @param signupService
   */
  constructor(private store: Store<AppState>, private fb: FormBuilder,
              private signupService: SignupService, private countryService: CountryService,
              private countryStatesService: CountryStatesService, private postalCodeService: PostalCodeService) {
    this.countries$ = this.countryService.getCountriesList();
    this.states$ = this.countryStatesService.getStates(this.usCountryId);
  }

  ngOnInit(): void {
    this.createForm();
    this.createShipToForm();
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
      addressLine2: ['', Validators.required],
      states: null,
      city: '',
      isGuest: 1,
      postalCode: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      country: ['', Validators.required]
    }, {validator: PasswordValidation.MatchPassword});

    this.userForm.valueChanges.debounceTime(1000)
      .subscribe(data => this.onValueChanged(data));

    // this.onValueChanged();
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
   * method 'createShipToForm' to create form fields and apply validations for shipping address
   * @param address
   */

  createShipToForm(address?): void {
    this.shipToForm = this.fb.group({
      emailAddressShip: [(address !== undefined) ? this.userForm.value.emailAddress : '',
        [Validators.required, Validators.pattern(EMAIL_REGEX)]],
      addressLine1: [(address !== undefined) ? address.addressLine1 : '', Validators.required],
      addressLine2: [(address !== undefined) ? address.addressLine2 : '', Validators.required],
      states: (address !== undefined) ? address.state : null,
      city: (address !== undefined) ? address.city : '',
      shipPostalCode: [(address !== undefined) ? address.postalCode : '', Validators.required],
      phoneNumber: [(address !== undefined) ? address.phoneNumber : '', Validators.required],
      country: [(address !== undefined) ? address.country : '', Validators.required]
    });

    this.shipToForm.valueChanges.debounceTime(1000)
      .subscribe(data => this.onShipToValueChanged(data));

    // this.onShipToValueChanged();
  }

  /**
   * On key value changed event
   * @param data
   */
  onShipToValueChanged(data?: any) {
    if (!this.shipToForm) {
      return;
    }
    const form = this.shipToForm;

    this.recheckFieldsForValidationErrors(form);
    this.doValidateZipCode(form);
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
        }
      }
    }
  }

  doValidateZipCode(form) {
    const city = form.get('city');
    const state = form.get('states');
    const postalCode = form.get('shipPostalCode');
    const country = form.get('country');

    // If Selected Country Is U.S
    if (country.value == 230) {
      const strCity = city.value || '0';
      const strStateId = state.value || '0';
      const strPostalCode = postalCode.value || '0';
      const strCountryId = country.value;

      if (strPostalCode.length > 3 && (postalCode.dirty || city.dirty || state.dirty || country.dirty)) {

        this.postalCodeService.isPostalCodeValid(strPostalCode, strCity, strStateId, strCountryId).subscribe((d: any) => {
          const response = JSON.parse(d._body);

          // If error property is present in json, that mean the postalCode is not correct
          if (response.error) {
            postalCode.setErrors({wrongZip: true});
            city.setErrors({wrongZip: true});
            state.setErrors({wrongZip: true});
            postalCode.setErrors({wrongZip: true});
            country.setErrors({wrongZip: true});

            city.markAsDirty();
            state.markAsDirty();
            postalCode.markAsDirty();
            country.markAsDirty();
            form.markAsDirty();

            this.recheckFieldsForValidationErrors(form);
            // form.updateValueAndValidity();
          } else {
            this.store.dispatch({type: IS_SHIPPING_FORM_VALID, payload: {isFormValid: true}});
          }

        });
      } else {
        city.markAsDirty();
        state.markAsDirty();
        postalCode.markAsDirty();
        country.markAsDirty();

        postalCode.setErrors({wrongZip: true});
        city.setErrors({wrongZip: true});
        state.setErrors({wrongZip: true});
        country.setErrors({wrongZip: true});

        // form.markAsDirty();
      }
    }else {
      this.store.dispatch({type: IS_SHIPPING_FORM_VALID, payload: {isFormValid: true}});
    }
    this.recheckFieldsForValidationErrors(form);
    // form.updateValueAndValidity();

    // if (form.invalid) {
    //   this.store.dispatch({type: IS_SHIPPING_FORM_VALID, payload: {isFormValid: false}});
    // } else {
    //   this.store.dispatch({type: IS_SHIPPING_FORM_VALID, payload: {isFormValid: true}});
    // }
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
    'emailAddressShip': {
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

  /**
   * this function fill shipTo Address same as billing address
   */
  sameAddress(event) {
    const billAddress = {
      emailAddress: this.userForm.value.emailAddress,
      addressLine1: this.userForm.value.addressLine1,
      addressLine2: this.userForm.value.addressLine2,
      city: this.userForm.value.city,
      state: this.userForm.value.states,
      postalCode: this.userForm.value.postalCode,
      country: this.userForm.value.country,
      phoneNumber: this.userForm.value.phoneNumber
    };
    let shipToForm;
    if (event.target.checked) {
      this.createShipToForm(billAddress);
      shipToForm = this.shipToForm;
      setTimeout(function(){
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
      shipToForm = this.shipToForm;
      setTimeout(function(){
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

  ngOnDestroy(): void {

    this.store.dispatch({
      type: 'guestState',
      payload: {userForm: this.userForm.value, shipToForm: this.shipToForm.value}
    });
    // alert('yes i can call the service here');
  }
}
