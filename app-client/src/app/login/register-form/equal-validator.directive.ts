import { Directive, forwardRef, Attribute } from '@angular/core';
import { Validator, AbstractControl, NG_VALIDATORS } from '@angular/forms';

@Directive({
    selector: '[validateEqual][formControlName],[validateEqual][formControl],[validateEqual][ngModel]',
    providers: [
        { provide: NG_VALIDATORS, useExisting: forwardRef(() => PasswordValidation), multi: true }
    ]
})

export class PasswordValidation {

    static MatchPassword(AC: AbstractControl) {
        let password = AC.get('password').value; // to get value in input tag
        let confirmPassword = AC.get('cPassword').value; // to get value in input tag
        if (password.pristine || confirmPassword.pristine) {
            return null;
        }
        if (password === confirmPassword) {
            return null;
        } else {
            AC.get('cPassword').setErrors({ MatchPassword: true });
        }
    }
    static ProfileMatchPassword(AC: AbstractControl) {
        let password = AC.get('newPassword').value; // to get value in input tag
        let confirmPassword = AC.get('confirmPassword').value; // to get value in input tag
        if (password.pristine || confirmPassword.pristine) {
            return null;
        }
        if (password === confirmPassword) {
            return null;
        } else {
            AC.get('confirmPassword').setErrors({ MatchPassword: true });
        }
    }
}
