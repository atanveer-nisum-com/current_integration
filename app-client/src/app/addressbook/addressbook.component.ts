import { Router } from '@angular/router';
/**
 * @author Abdul Ghaffar Mallah
 * */
import swal from 'sweetalert2';
import { Component, OnInit } from '@angular/core';
import { Address } from '../models/address';
import { AbstractControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { AddressBookService } from '../service/address-book.service';
import { CountryService } from '../service/country.service';
import { CountryStatesService } from '../service/country-states.service';
import { PostalCodeService } from '../service/postalcode.service';
import { Observable } from 'rxjs/Observable';
import { Country } from '../models/country';
import { CountryState } from '../models/state';
import 'rxjs/add/observable/of';
import 'rxjs/add/operator/mergeMap';
import 'rxjs/add/operator/map';

@Component({
  selector: 'app-address-book',
  templateUrl: 'address-book.component.html',
  styleUrls: ['address-book.component.css'],
  providers: [AddressBookService, CountryStatesService, CountryService, PostalCodeService]
})
export class AddressBookComponent implements OnInit {
  addresses: Address[];
  shippingForm: FormGroup;
  isEditForm: boolean;
  showForm = false;
  formType: string;
  usCountryId = 230;
  userId: number;
  limitExced;

  countries$: Observable<Country>;
  states$: Observable<CountryState>;

  initialShippingAddress: Address = {
    id: null,
    addressLine1: null,
    addressLine2: null,
    addressType: 1,
    phoneNumber: null,
    city: null,
    zipcode: null,
    countryBean: null,
    stateBean: null,
    isDefault: 0
  };

  formErrors = {
    'addressLine1': '',
    'zipcode': '',
    'phoneNumber': '',
    'countryBean': ''
  };

  validationMessages = {
    'addressLine1': {
      'required': 'Address is required.',
    },
    'zipcode': {
      'required': 'Zip Code is required.',
      'wrongZip': 'Zip Code, City, State does not match.'
    },
    'phoneNumber': {
      'required': 'Phone Number is required.',
    },
    'countryBean': {
      'required': 'Country is required'
    }
  };

  constructor(private addressBookService: AddressBookService, private fb: FormBuilder,
    private countryService: CountryService, private countryStatesService: CountryStatesService,
    private postalCodeService: PostalCodeService, private router: Router) {
  }

  ngOnInit(): void {
    if (localStorage.getItem('isLoged') == 'false') {
      this.router.navigateByUrl('/login');
      localStorage.setItem('oldstate', this.router.url);
    }
    this.createShippingAddressForm();
    this.countries$ = this.countryService.getCountriesList();
    this.states$ = this.countryStatesService.getStates(this.usCountryId);

    this.userId = +localStorage.getItem('user');

    this.addressBookService.getAllShippingAddresses(this.userId).subscribe(data => {
      this.limitExced = false;
      this.addresses = data.content;
    });
  }

  showEditShippingAddressForm(shippingAddress: Address) {
    this.showForm = true;
    this.isEditForm = true;
    this.formType = 'Edit Address';

    this.createShippingAddressForm(shippingAddress);
  }

  showAddNewShippingAddressForm() {
    this.showForm = true;
    this.isEditForm = false;
    this.formType = 'Add New Address';

    this.createShippingAddressForm();
  }

  deleteShippingAddress(shippingAddressId: number) {
    this.addressBookService.deleteShippingAddress(shippingAddressId).subscribe(data => this.addresses = data.content);
    this.limitExced = false;
    if (this.showForm) {
      this.showAddNewShippingAddressForm();
    }

  }

  createShippingAddressForm(address: Address = this.initialShippingAddress): void {

    this.shippingForm = this.fb.group({
      id: address.id,
      addressLine1: [address.addressLine1, Validators.required],
      addressLine2: address.addressLine2,
      stateBean: address.stateBean ? address.stateBean.id : '',
      city: address.city,
      zipcode: [address.zipcode, Validators.required],
      phoneNumber: [address.phoneNumber, Validators.required],
      countryBean: [address.countryBean ? address.countryBean.id : '', Validators.required],
      isDefault: address.isDefault
    });
    this.shippingForm.valueChanges.debounceTime(800).subscribe(d => {
      this.doValidateZipCode(this.shippingForm).debounceTime(800).subscribe(data => {
        const city = this.shippingForm.get('city');
        const state = this.shippingForm.get('stateBean');
        const postalCode: AbstractControl = this.shippingForm.get('zipcode');
        const country = this.shippingForm.get('countryBean');

        if (data.error) {
          postalCode.setErrors({ wrongZip: true });
          postalCode.markAsDirty();
        }

        for (const field in this.formErrors) {
          // clear previous error message (if any)
          this.formErrors[field] = '';
          const control = this.shippingForm.get(field);

          if (control && control.dirty && !control.valid) {
            const messages = this.validationMessages[field];
            for (const key in control.errors) {
              if (country.value != this.usCountryId) {
                this.formErrors['stateBean'] = '';
                continue;
              }
              this.formErrors[field] += messages[key] + ' ';
            }
          }
        }

      });
    });
  }

  doValidateZipCode(form): Observable<any> {

    let dumyObs: Observable<any> = Observable.of({});

    const city = form.get('city');
    const state = form.get('stateBean');
    const postalCode: AbstractControl = form.get('zipcode');
    const country = form.get('countryBean');

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

    return dumyObs;
  }

  onSubmit() {
    if (!this.shippingForm.invalid) {
      const formValues = this.shippingForm.value;

      const address: Address = {
        id: formValues.id,
        userId: this.userId,
        addressLine1: formValues.addressLine1,
        addressLine2: formValues.addressLine2,
        addressType: 1,
        phoneNumber: formValues.phoneNumber,
        city: formValues.city,
        stateBean: formValues.countryBean == this.usCountryId ? { id: formValues.stateBean } : null,
        zipcode: formValues.zipcode,
        countryBean: {
          id: formValues.countryBean
        },
        isDefault: formValues.isDefault ? 1 : 0
      };

      if (this.isEditForm) {
        this.addressBookService.updateShippingAddress(address).subscribe(data => {
          this.addresses = data.content;
          this.limitExced = false;
        });
      } else {
        this.addressBookService.addNewShippingAddress(address).subscribe(data => {
          this.addresses = data.content;
          this.limitExced = false;
        }, error => {
          this.limitExced = true;
          this.limitExceedPopUp();
        });
      }
    } else {
      Object.keys(this.shippingForm.controls).forEach(key => {
        const control = this.shippingForm.get(key);
        if (key == 'stateBean') {
          const countryControl = this.shippingForm.get('countryBean');
          if (countryControl.value != this.usCountryId) {
            return;
          }
        }
        control.markAsDirty();
      });
      this.shippingForm.updateValueAndValidity();
    }
  }

  limitExceedPopUp() {
    if (this.limitExced) {
      swal('Limit Exceed', 'You can add only upto 10 addresses', 'error');
    }
  }

}
