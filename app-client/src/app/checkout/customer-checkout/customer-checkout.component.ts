import { LoginService } from './../../service/login.service';
import { User } from './../../models/user';
import { GuestCheckoutComponent } from './../guest-checkout/guest-checkout.component';
import { RegisterCheckoutComponent } from './../register-checkout/register-checkout.component';
import { Component, OnInit } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'app-customer-checkout',
  templateUrl: 'customer-checkout.component.html',
  styleUrls: ['customer-checkout.component.css'],
  providers: [LoginService]
})
export class CustomerCheckoutComponent implements OnInit {


  userData: User;
  loginService: LoginService;
  componentData = null;

  constructor(_loginService: LoginService) {
    this.loginService = _loginService;
  }

  ngOnInit() {
    // Called after the constructor, initializing input properties, and the first call to ngOnChanges.
    // Add 'implements OnInit' to the class.
if (localStorage.getItem('userToken') !== null && localStorage.getItem('user') !== null) {

      // tslint:disable-next-line:radix
      const userAuth = { 'token': localStorage.getItem('userToken'), 'userId': parseInt(localStorage.getItem('user')) };

      this.loginService.getUserObject(userAuth).subscribe((res: User) => {
        this.userData = res;
      });
    }
  }

  RegisterCheckoutComp() {
    this.componentData = {
      component: RegisterCheckoutComponent
    };
  }

  GuestCheckOutComp() {
    this.componentData = {
      component: GuestCheckoutComponent
    };
  }
}
