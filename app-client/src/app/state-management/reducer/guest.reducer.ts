import { GuestState } from './../state/guest-checkout-state';
/**
 * Reducer for Cart
 *
 * @author Mirza Touseef Baig
 */

// Importing
import { Action } from '@ngrx/store';

// Custom files
import { CartService } from './../../service/cart.service';
import { CART_ADD_ITEM } from './../action/cart.action';
import { CartState } from './../state/cart.state';

/**
 * Initial state for cart
 */
const initialState: GuestState = {
    /**
     * Reflects guest Checkout data,
     * null indicates that JSON has no value
     */
    data: null
};

/**
 * This function is called when Store dispatches Cart Action.
 * @param state current state of the Guest
 * @param action action dispatched by Store
 * @return {any} response depend on the type of action dispatched by Store.
 */
export function guestCheckoutReducer(state: GuestState = initialState, action: Action): any {
    switch (action.type) {
        case 'guestState':
            return { data: action.payload };
        default:
            return state;
    }
}
