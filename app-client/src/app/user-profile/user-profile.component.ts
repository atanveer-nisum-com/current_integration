
import { Optional } from '@angular/core';
import { IS_SHIPPING_FORM_VALID } from './../state-management/action/form.action';
import { AppState } from './../state-management/state/app.state';
import { Store } from '@ngrx/store';

import { PostalCodeService } from './../service/postalcode.service';
import { Profile } from './../models/profile';
import { Address } from './../models/address';
import { UserProfileService } from './../service/user-profile.service';
import swal from 'sweetalert2';
import { PasswordValidation } from './../login/register-form/equal-validator.directive';
import { EMAIL_REGEX } from './../shared/utility';
import { Router } from '@angular/router';
import { CountryService } from './../service/country.service'
import { CountryStatesService } from './../service/country-states.service';
import { CountryState } from './../models/state';
import { Country } from './../models/country';
import { FormBuilder, FormGroup, FormControl, Validators, AbstractControl } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
@Component({
    moduleId: module.id,
    selector: 'user-profile',
    templateUrl: 'user-profile.component.html',
    styleUrls: ['user-profile.component.css'],
    providers: [UserProfileService, CountryService, CountryStatesService,PostalCodeService]
})

export class UserProfileComponent implements OnInit {
    registerFormTitle = 'Create a profile';
    cities = ['Really Smart', 'Super Flexible', 'Weather Changer'];
    countries$: Observable<Country>;
    // states = [{state: 'ALASKA', code: 'AK'}, {state: 'ALABAMA', code: 'AL'}, {
    //   state: 'ARKANSAS',
    //   code: 'AR'
    // }, {state: 'ARIZONA', code: 'AZ'}];
    userForm: FormGroup;
    userData: Profile;
    states$: Observable<CountryState>;
    location: Location;
    previousUrl: string;
    billingAddres:Address;
    // router: Router;
    usCountryId = 230;
    /**
     *
     * @param fb
     *
     */
    constructor(private store: Store<AppState>,private fb: FormBuilder,
        private userProfileService: UserProfileService,
        private countryService: CountryService,
        private countryStatesService: CountryStatesService,
        private router: Router,private postalCodeService: PostalCodeService
    ) {

        this.countries$ = this.countryService.getCountriesList();
        this.states$ = this.countryStatesService.getStates(this.usCountryId);
        // this.router = _router;

    }

    ngOnInit(): void {
        
        console.log('Order Histroy');
        console.log(localStorage.getItem('isLoged'));
        if(localStorage.getItem('isLoged')=='false'){
            this.router.navigateByUrl('/login');
            localStorage.setItem('oldstate',this.router.url);
        }else {
            this.createForm();

            this.userProfileService.getUserProfile(+localStorage.getItem('user'))
            .subscribe(user => {

                this.userData = user;
              
                for (var i = 0; i < this.userData.addresses.length; i++) {
                    if(this.userData.addresses[i].addressType==1){
                        this.billingAddres=this.userData.addresses[i];
                    }
                    
                }
                this.userForm = this.fb.group({

                    countryid:[this.billingAddres.countryBean.id],
                    userId:[this.userData.id],
                    addressid:[this.billingAddres.id],
                    firstName: [this.userData.firstName, Validators.required],
                    lastName: [this.userData.lastName, Validators.required],
                    emailAddress: [this.userData.emailAddress, [Validators.required, Validators.pattern(EMAIL_REGEX)]],
                    addressLine1: [this.billingAddres.addressLine1, Validators.required],
                    addressLine2: [this.billingAddres.addressLine2, Validators.required],
                    states: [this.billingAddres.stateBean?this.billingAddres.stateBean.id:''],
                   // password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
                   // cPassword: ['', Validators.required],
                   
                    city: this.billingAddres.city,
                    postalCode: [this.billingAddres.zipcode, Validators.required],
                    phoneNumber: [this.billingAddres.phoneNumber, Validators.required],
                    country: [this.billingAddres.countryBean.id, Validators.required],
                    
                    currentPassword: [''],
                    newPassword: [''],
                    confirmPassword: [''],
                },{ validator: PasswordValidation.ProfileMatchPassword }) ;

                this.userForm.valueChanges.debounceTime(1000).subscribe(data => this.onValueChanged(data));
                
                this.onValueChanged();
            });
        }
    }
    /**
     * method 'createForm' to create form fields and apply validations
     */
    createForm(): void {
        this.userForm = this.fb.group({
            countryid:['', Validators.required],
            userId:['', Validators.required],
            addressid:['', Validators.required],
            password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
            cPassword: ['', Validators.required],
            firstName: ['', Validators.required],
            lastName: ['', Validators.required],
            emailAddress: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]],
            addressLine1: ['', Validators.required],
            addressLine2: ['', Validators.required],
            states: '',
            city: '',
            postalCode: ['', Validators.required],
            phoneNumber: ['', Validators.required],
            country: ['', Validators.required],
            currentPassword: ['', [ Validators.minLength(6), Validators.maxLength(25)]],
            newPassword: ['',[ Validators.minLength(6), Validators.maxLength(25)]],
            confirmPassword: ''
        },{ validator: PasswordValidation.ProfileMatchPassword });
        

    }

    /**
     * On key value changed event
     * @param data
     */
    onValueChanged(data?: any) {
        console.log('on value change');
        
        if (!this.userForm) {
            return;
        }
        const form = this.userForm;
        this.doValidateZipCode(form).debounceTime(800).subscribe(data => {
            const city = this.userForm.get('city');
            const state = this.userForm.get('states');
            const postalCode = this.userForm.get('postalCode');
            const country = this.userForm.get('country');
            const pass=this.userForm.get('currentPassword');
           

            if(JSON.stringify(pass.value).length>3){
                let newPasswordRequired = this.userForm.get('newPassword');
                newPasswordRequired.setValidators([Validators.required,  Validators.minLength(6), Validators.maxLength(25)]);
                newPasswordRequired.updateValueAndValidity();
            }
            


            if (data.error) {
              postalCode.setErrors({ wrongZip: true });
              postalCode.markAsDirty();
              this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
            } else {
              if (!data.error && country.value == this.usCountryId) {
                this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: false } });
              } else {
                this.store.dispatch({ type: IS_SHIPPING_FORM_VALID, payload: { isFormValid: true } });
              }
            }
      
            for (const field in this.formErrors) {
              // clear previous error message (if any)
              this.formErrors[field] = '';
              const control = this.userForm.get(field);
      
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
          });
        this.recheckFieldsForValidationErrors(form);
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

    doValidateZipCode(form): Observable<any> {
        
            let dumyObs: Observable<any> = Observable.of({});
        
            const city = form.get('city');
            const state = form.get('states');
            const postalCode = form.get('postalCode');
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
        
            return dumyObs;
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
        'phoneNumber': '',
        'addressLine1':'',
        'addressLine2':'',
        'city':'',
        'currentPassword':'',
        'newPassword':'',
        'confirmPassword':'',
        'newPasswordRequired':''    };

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
        'addressLine1': {
            'required': 'Address Line1 is required.',
        },
        'addressLine2': {
            'required': 'Address Line2 is required.',
        },
        
        'city': {
            'required': 'City is required.',
        },
        'postalCode': {
            'required': 'Zip Code is required.',
            'wrongZip': 'Zip Code, City, State does not match.'
        },
        'phoneNumber': {
            'required': 'Phone Number is required.',
        }
        ,
        
        'currentPassword': {
            'required': 'Must be 6-25 characters long.',
            'minlength': 'Password should be greater than 6 characters',
            'maxlength': 'Password exceeded than 25 characters',
        },
        'newPassword': {
            'required': 'New password is required.',
            'minlength': 'Password should be greater than 6 characters',
            'maxlength': 'Password exceeded than 25 characters',
        },


        'confirmPassword': {
            'required': 'Password Mismatch.'
        },
        'newPasswordRequired': {
            'required': 'New password is required.',
            'wrongZip': 'Zip Code, City, State does not match.'
        },
    };


 

    /**
     * 'onSubmit' method call signup api and return response
     */
    onSubmit() {
        console.log('On Sumbit');
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
        const userModel: Profile = {
            id:formData.userId,
            firstName: formData.firstName,
            lastName: formData.lastName,
            emailAddress: formData.emailAddress,
            //password: formData.password,
            //cPassword:formData.cPassword,
            currentPassword:formData.currentPassword,
            newPassword:formData.newPassword,
            confirmPassword:formData.confirmPassword,
            addresses: [{
                id:formData.addressid,
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
        // this.checkFieldsForValidationErrors(this.userForm)
        if (this.userForm.valid) {
            console.log('Button Pressed');
            this.userProfileService.updateUserProfile(userModel).subscribe(data => {
                
                swal('Success', 'Your profile has been updated successfully.', 'success');
                if (location.hostname === 'localhost') {

                }
            }, err => {
                const errorMsg = JSON.parse(err._body || '{error: Request Cant Be Completed Because Of Some Problem!}').error;
                swal('', errorMsg,
                    'error'
                );
            });
        } else {
            swal('Form Incomplete', 'Kindly fill the required fields',
                'info'
            );
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
