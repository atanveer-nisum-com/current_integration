import { CART_REMOVE_ITEM } from './../action/cart.action';
/**
 * Reducer for Cart
 * 
 * @author Abdul Ghaffar Mallah
 */

//Importing
import { Action } from '@ngrx/store';

//Custom files
import { CartService } from './../../service/cart.service';
import { CART_ADD_ITEM } from './../action/cart.action';
import { CartState } from './../state/cart.state';

/**
 * Initial state for cart
 */
const initialState: CartState = {
    /**
     * Reflects current selected item's id,
     * -1 indicates that no item is selected
     */
    selectedItemId: -1,
    itemCount: undefined
};

/**
 * This function is called when Store dispatches Cart Action.
 * 
 * @param state current state of the Cart
 * @param action action dispatched by Store
 * @return {any} response depend on the type of action dispatched by Store.
 */
export function cartReducer(state: CartState = initialState, action: Action): any {
    // console.log("type: " + action.type);
    switch (action.type) {
        case CART_ADD_ITEM:
            return { selectedItemId: action.payload, itemCount: undefined };
        case CART_REMOVE_ITEM:
            return { itemCount: action.payload, selectedItemId: undefined };
        default:
            return state;
    }
}