import {FormState} from "../state/form.state";
import {Action} from "@ngrx/store";
import {IS_SHIPPING_FORM_VALID} from "../action/form.action";

const initialState: FormState = {
  /**
   * Reflects guest Checkout data,
   * null indicates that JSON has no value
   */
  isFormValid: false
};

export function formReducer(state: FormState = initialState, action: Action): any {
  switch (action.type) {
    case IS_SHIPPING_FORM_VALID:
      return {isFormValid: action.payload.isFormValid};
    default:
      return state;
  }
}
