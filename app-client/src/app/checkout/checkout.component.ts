import { LoginService } from './../service/login.service';
import { CART_REMOVE_ITEM } from './../state-management/action/cart.action';
import { SET_USER_LOGIN } from './../state-management/action/user.action';
import { CheckoutService } from './../service/checkout-service';
import { CartService } from './../service/cart.service';
import { Country } from './../models/country';
import { CountryState } from './../models/state';
import { Address } from './../models/address';
import { User } from './../models/user';
import { GuestState } from './../state-management/state/guest-checkout-state';
import { AppState } from './../state-management/state/app.state';
import { Store } from '@ngrx/store';
import { ShipmentMethodComponent } from './shipment-method/shipment-method.component';
import { CartViewComponent } from './../cart/cart-view/cart-view.component';
import { RegisterCheckoutComponent } from './register-checkout/register-checkout.component';
import { GuestCheckoutComponent } from './guest-checkout/guest-checkout.component';
import { Component } from '@angular/core';
import { FormState } from "../state-management/state/form.state";
import { AddressBookService } from "../service/address-book.service";
import swal from 'sweetalert2';

@Component({
  moduleId: module.id,
  selector: 'app-checkout',
  templateUrl: 'checkout.component.html',
  styleUrls: ['checkout.component.css'],
  providers: [CheckoutService, AddressBookService, LoginService]
})
export class CheckoutComponent {

  /**
   *  Indicates the last step number in checkout process
   * */
  lastStep = 2;

  /**
   *  Holds the msg shown while checkout wizard process
   *  Ex:
   *    Step 1
   *    Step 2
   *    Final Step
   * */
  stepMsg;

  componentData = null;
  user: User;
  checkoutService: CheckoutService;
  addresses: Address[];
  // store: Store<AppState>;
  isShippingFormValid = false;
  compNumber: any = 0;
  component = [
    {
      compName: ShipmentMethodComponent
    },
    {
      compName: CartViewComponent
    }
  ];


  getComponent() {

    if (this.isShippingFormValid === true) {

      /**
       *  If checkout step is less then last step then show 'Step + currentStepNumber' in checkout process
       *  else show 'Final Step' message.
       * */
      if (this.compNumber + 1 < this.lastStep) {
        this.stepMsg = 'Step ' + (this.compNumber + 2);
      } else {
        this.stepMsg = 'Final Step';
      }

      this.componentData = {
        component: this.component[this.compNumber].compName,
        inputs: {
          isDisplay: true
        }
      };

      const userData = localStorage.getItem('checkoutInfo');

      if (userData) {
        this.processData(userData);
      }

      this.compNumber++;
    }

  }

  processData(data) {

    const obj = JSON.parse(data);
    const userForm = obj.userform;
    const shipToForm = obj.shipForm;

    console.log('USERFORM: ' + JSON.stringify(userForm));
    console.log('SHIPFORM: ' + JSON.stringify(shipToForm));

    let shipToAddress: Address;
    if (shipToForm.addressLine1 !== '') {

      const shipToStates: CountryState = {
        id: shipToForm.states
      };
      const shipToCountry: Country = {
        id: shipToForm.country
      };

      shipToAddress = {
        id: shipToForm ? shipToForm.addressId : -1,
        addressLine1: shipToForm.addressLine1,
        addressLine2: shipToForm.addressLine2,
        addressType: 1,
        phoneNumber: shipToForm.phoneNumber,
        zipcode: shipToForm.shipPostalCode,
        city: shipToForm.city,
        stateBean: shipToStates,
        countryBean: shipToCountry
      };
    }

    const userStates: CountryState = {
      id: userForm.states
    };
    const userCountry: Country = {
      id: userForm.country
    };
    const userAddress: Address = {
      id: userForm ? userForm.addressId : -1,
      addressLine1: userForm.addressLine1,
      addressLine2: userForm.addressLine2,
      addressType: 0,
      phoneNumber: userForm.phoneNumber,
      zipcode: userForm.postalCode,
      city: userForm.city,
      stateBean: userStates,
      countryBean: userCountry
    };

    const user: User = {
      userId: parseInt(localStorage.getItem('user'), 0),
      firstName: userForm.firstName,
      lastName: userForm.lastName,
      isGuest: userForm.isGuest,
      emailAddress: userForm.emailAddress,
      password: userForm.password,
      cPassword: userForm.cPassword,
      addresses: [userAddress, shipToAddress],
    };

    if (this.isShippingFormValid) {

      localStorage.setItem('userObj', JSON.stringify(user));
      console.log('this is user object :: ' + JSON.stringify(user));
      this.checkoutService.saveUser(user).debounceTime(1000).subscribe(res1 => {
        console.log('Saving User');
        localStorage.removeItem('checkoutInfo');
        console.log('userID: ' + localStorage.getItem('userToken'));
        if (!localStorage.getItem('userToken')) {
          console.log('loging user');
          user.rememberMe = false;
          console.log('loging rememberMe');
          console.log(JSON.stringify(user));
          this.loginService.doLogin(user).subscribe(res => {
            if (res.userId) {
              localStorage.setItem('user', res.userId);
              localStorage.setItem('userToken', res.token);
              this.store.dispatch({ type: SET_USER_LOGIN, payload: true });
              this.store.dispatch({ type: CART_REMOVE_ITEM, payload: res.cartCount });
              console.log('run success');
            }
          });
        }

      });
    }
  }


  constructor(private store: Store<AppState>, _checkoutService: CheckoutService,
    private loginService: LoginService, private _addressBookService: AddressBookService) {

    /**
     * Initial Step Msg shown at checkout process page (Step 1)
     * */
    this.stepMsg = 'Step ' + (this.compNumber + 1);

    this.checkoutService = _checkoutService;
    const tmpThis = this;
    store.select('formState').skip(1).subscribe((data: FormState) => {
      if (data.isFormValid) {
        tmpThis.isShippingFormValid = true;
      } else {
        tmpThis.isShippingFormValid = false;
      }

      console.log(tmpThis.isShippingFormValid);
    });
  }
}
