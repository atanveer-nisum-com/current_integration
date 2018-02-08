import { CART_REMOVE_ITEM } from './../state-management/action/cart.action';
import { SET_USER_LOGIN } from './../state-management/action/user.action';
import { Router, NavigationError } from '@angular/router';
import { LoginService } from './../service/login.service';
import { UserState } from './../state-management/state/user.state';
import { CartService } from './../service/cart.service';
import { CartState } from './../state-management/state/cart.state';
import { AppState } from './../state-management/state/app.state';
import { Component } from '@angular/core';
import { Store } from '@ngrx/store';
import { Response } from '@angular/http';
import  swal from 'sweetalert2';


@Component({
    selector: 'app-header',
    templateUrl: 'header.component.html',
    styleUrls: ['header.component.css']
})
export class HeaderComponent {
    private noOfItemsInCart: number = 0;
    userId: number = 0;
    userState = 'Login';


    constructor(private store: Store<AppState>, private cartService: CartService, private router: Router) {
        if (localStorage.getItem('user')) {
            this.noOfItemsInCart = parseInt(localStorage.getItem('noOfItems'), 0) || 0;
            this.userId = parseInt(localStorage.getItem('user'), 0) || 0;
        }
        if (localStorage.getItem("isLogned")){
            this.subscribeUserState();
        }

        store.select('cartState').subscribe(
            (data: CartState) => {
                this.noOfItemsInCart = +localStorage.getItem('noOfItems');
                if (data.selectedItemId) {
                    if (data.selectedItemId <= 0) {
                        return;
                    }
                    if (localStorage.getItem('user')) {
                        this.userId = parseInt(localStorage.getItem('user'), 0);
                    }
                    this.cartService.addItem(this.userId, data.selectedItemId).subscribe(
                        res => {
                            this.noOfItemsInCart = res.totalItem;
                            this.userId = res.user.id;
                            localStorage.setItem('user', '' + this.userId);
                            localStorage.setItem('noOfItems', '' + this.noOfItemsInCart);
                        },
                        (error: Response) => {
                            swal('Out of Stock', error.json().error, 'error');
                            return false;
                        }
                    );
                }
                this.noOfItemsInCart = (data.itemCount) ? data.itemCount : this.noOfItemsInCart;
                if (data.itemCount === 0) { this.noOfItemsInCart = 0; }
                localStorage.setItem('noOfItems', '' + this.noOfItemsInCart);
            } 
        );  

        this.subscribeUserState();
    }

    /**
     * this method get the state of the user
     */
    subscribeUserState() {
        
        this.store.select('userState').subscribe((data: UserState) => {
            if (localStorage.getItem('isLoged')) {
                this.userState = localStorage.getItem('isLoged') == 'true'? 'Logout' : 'Login';
            }
            if (!data) {
                return;
            }
            if (data.isLoged || localStorage.getItem('isLoged') == 'true' ) {
                this.userState = 'Logout';
                localStorage.setItem('isLoged', 'true');
            } else {
                this.userState = 'Login';
                localStorage.setItem('isLoged', 'false');
            }
        });
    }

    navigate() {
        if (localStorage.getItem('isLoged') == 'true') {
            this.userLogout();

        } else {
            this.userState = 'Login';
            this.router.navigate(['/login']);
        }

    }

    userLogout() {
        this.store.dispatch({ type: SET_USER_LOGIN, payload: false });
        this.store.dispatch({ type: CART_REMOVE_ITEM, payload: 0 });
        localStorage.removeItem('user');
        localStorage.removeItem('userToken');
        localStorage.setItem('isLoged', 'false');
        this.userState = 'Login';
        this.userId = 0;
        this.router.navigate(['/']);
    }
}
