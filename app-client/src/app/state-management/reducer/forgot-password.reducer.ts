
/**
 * Reducer for Cart
 *
 * @author Shaharyar Iqbal
 */

//Importing
import { Action } from '@ngrx/store';

//Custom files
import { SET_MESSAGE } from './../action/forgot-password.action';
import { ForgotPasswordState } from './../state/forgot-password.state';

/**
 * Initial state for cart
 */
const initialState: ForgotPasswordState = {
    /**
     * Reflects messages over all forgot scenerio,
     *
     */
    message: null,
};

/**
 * This function is called when Store dispatches ForgotPasswordState Action.
 *
 * @param state current state of the ForgotPasswordState
 * @param action action dispatched by Store
 * @return {any} response depend on the type of action dispatched by Store.
 */
export function forgotPasswordReducer(state: ForgotPasswordState = initialState, action: Action): any {
    switch (action.type) {
        case SET_MESSAGE:
            return { message: action.payload };
        default:
            return state;
    }
}
