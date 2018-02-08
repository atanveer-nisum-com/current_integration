import { Store } from '@ngrx/store';
import { AppState } from './../../state-management/state/app.state';
import { CART_REMOVE_ITEM } from './../../state-management/action/cart.action';
import { User } from './../../models/user';
import { CheckoutService } from './../../service/checkout-service';
import {Component, NgModule, ElementRef, NgZone, Input} from '@angular/core';
import { Router, NavigationStart } from '@angular/router';



export let PayPalButtonModule = paypal.Button.driver('angular2', {Component, NgModule, ElementRef, NgZone});
let thisCopy: any;
@Component({
  template: '<paypal-button [props]="{ payment: payment, onAuthorize: onAuthorize, env:\'sandbox\' }" ></paypal-button>',
  selector: 'app-paypal-checkout-button',
  providers: [CheckoutService]
})

export class PaypalCheckoutComponent {



  constructor(private router: Router, private checkoutService: CheckoutService,private store: Store<AppState>) {
  }


  payment() {

    const userObj = localStorage.getItem('userObj');

    return paypal.request({
      method: 'post',
      url: 'http://localhost:8080/api/paypal/create',
      json: JSON.parse(userObj)
    }).then(function (data) {
      return data.id;
    });
  }

  onAuthorize = (datap, actions) => {
    const userObj: User = JSON.parse(localStorage.getItem('userObj'));
    thisCopy = this;
   return paypal.request.post('http://localhost:8080/api/paypal/execute', {
      paymentId: datap.paymentID,
      payerId: datap.payerID
    }).then(function (data) {

      console.log(JSON.stringify(data));
      console.log(data.transactions[0].related_resources[0].sale.currency);
      paypal.request({
        method: 'post',
        url: 'http://localhost:8080/api/checkout/placeorder',
        json: {
          'transactionId': data.transactions[0].related_resources[0].sale.id,
          'userId': userObj.userId,
          'paymentTypeId': 1,
          'amount': data.transactions[0].related_resources[0].sale.amount.total,
          'currencyCode': data.transactions[0].related_resources[0].sale.amount.currency,
          'transactionStatus': 1,
          'comments': 'jaja',
          'orderId': null
        }
      }).then(() => {
        localStorage.setItem('noOfItems', '' + 0);
        thisCopy.store.dispatch({ type: CART_REMOVE_ITEM, payload: 0 });
        thisCopy.router.navigate(['/', 'orderHistory']);
      });
    });
  }
}

@NgModule({
  imports: [PayPalButtonModule],
  declarations: [PaypalCheckoutComponent],
  exports: [PaypalCheckoutComponent]

})
export class PaypalModule {
}
