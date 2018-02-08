import { ForgotPasswordState } from './forgot-password.state';
import { GuestState } from './guest-checkout-state';
/**
 *
 * State of the Application
 *
 * @author Abdul Ghaffar Mallah
 */

import { CartState } from './cart.state';
import {FormState} from "./form.state";

/**
 * AppState holds the state of application in context of Redux
 *
 * @type {interface}
 */
export interface AppState {
    /**
     * State of the cart
     * @type {CartState}
     */
    cartState?: CartState;

    /**
     * State of the cart
     * @type {GuestState}
     */
    guestState?: GuestState;
/**
     * State of the cart
     * @type {ForgotPasswordState}
     */
    forgotPasswordState?: ForgotPasswordState;

    formState?: FormState;
}
