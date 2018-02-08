
/**
 * Reducer for User
 *
 * @author Shaharyar Iqbal
 */

// Importing
import { Action } from '@ngrx/store';

// Custom files
import { LoginService } from './../../service/login.service';
import { UserState } from './../state/user.state';
import { SET_USER_LOGIN } from './../action/user.action';


/**
 * Initial state for cart
 */
const initialState: UserState = {
    /**
     * Reflects current selected user's,
     * false indicates that no user is loged in
     */
    isLoged: false
};

/**
 * This function is called when Store dispatches User Action.
 *
 * @param state current state of the User
 * @param action action dispatched by Store
 * @return {any} response depend on the type of action dispatched by Store.
 */
export function userReducer(state: UserState = initialState, action: Action): any {
    switch (action.type) {
        case SET_USER_LOGIN:
            return { isLoged: action.payload };
        default:
            return state;
    }
}
