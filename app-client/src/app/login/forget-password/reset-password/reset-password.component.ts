import { SET_USER_LOGIN } from './../../../state-management/action/user.action';
import { Store } from '@ngrx/store';
import { AppState } from './../../../state-management/state/app.state';
import { SET_MESSAGE } from './../../../state-management/action/forgot-password.action';
import { ActivatedRoute, Router } from '@angular/router';
import { PasswordValidation } from './../../register-form/equal-validator.directive';
import { EMAIL_REGEX } from './../../../shared/utility';
import { ResetPasswordService } from './../../../service/reset-password.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

/**
 * reset password component
 */
@Component({
    moduleId: module.id,
    selector: 'app-reset-password',
    templateUrl: 'reset-password.component.html',
    styleUrls: ['reset-password.component.css'],
    providers: [ResetPasswordService]
})
export class ResetPasswordComponent implements OnInit {

    resetPasswordForm: FormGroup;
    token;
    responseMessage;
    status;
    tokenfailure = false;
    message;
    /**
     * @constructor
     * @param formBuilder
     * @param ResetPasswordService
     */
    constructor(private fb: FormBuilder, private resetPassService: ResetPasswordService,
         private route: ActivatedRoute, private router: Router, private store: Store<AppState>) { }
    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.token = params['token'];
            this.getTokenStatus(this.token);
        });
        this.createResetPassForm();
    }
    createResetPassForm() {
        this.resetPasswordForm = this.fb.group({
            password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(25)]],
            cPassword: ['', Validators.required],
        }, { validator: PasswordValidation.MatchPassword });

        this.resetPasswordForm.valueChanges.debounceTime(800)
            .subscribe(data => this.onValueChanged(data));

        this.onValueChanged();
    }
    onValueChanged(data?: any) {
        if (!this.resetPasswordForm) {
            return;
        }
        const form = this.resetPasswordForm;


        for (const field in this.formErrors) {
            // clear previous error message (if any)
            this.formErrors[field] = '';
            const control = form.get(field);

            if (control && control.dirty && !control.valid) {
                const messages = this.validationMessages[field];
                // tslint:disable-next-line:forin
                for (const key in control.errors) {
                    this.formErrors[field] += messages[key] + ' ';
                }
            }
        }
    }

    getTokenStatus(token: string): void {
        this.resetPassService.isResetPasswordLinkValid(token)
            .subscribe(data => {
                if (data.status == 'error' || data.status == 'expired') {
                    this.responseMessage = data.message;
                    this.tokenfailure = true;
                }
            });
    }
    // tslint:disable-next-line:member-ordering
    formErrors = {
        'password': '',
        'cPassword': ''
    };

    /**
     * displaying form error messages while validation
     */
    // tslint:disable-next-line:member-ordering
    validationMessages = {
        'password': {
            'required': 'Must be 6-25 characters long.',
            'minlength': 'Password should be greater than 6 characters',
            'maxlength': 'Password exceeded than 25 characters',
        },
        'cPassword': {
            'required': 'Password Mismatch.',
        }
    };

    onSubmit() {
        if (this.resetPasswordForm.valid) {
            this.resetPassService.changePassword(this.token, this.resetPasswordForm.get('password').value,
                this.resetPasswordForm.get('cPassword').value).subscribe(data => {
                    // console.log('data', data);
                    if (data.status == 'success') {
                        // this.message = data.message;
                        this.store.dispatch({ type: SET_MESSAGE, payload: data.message });
                        this.store.dispatch({ type: SET_USER_LOGIN, payload: false });
                        this.router.navigate(['../login']);

                    } if (data.status == 'error') {
                        this.tokenfailure = true;
                        this.message = data.message;
                    } else {
                        //   swal('OOps..', 'Successfully Registered.', 'error');
                        this.tokenfailure = true;
                        this.message = data.message;
                    }
                });
        } else {
            this.resetPasswordForm.updateValueAndValidity();
            if (this.resetPasswordForm.invalid) {
                Object.keys(this.resetPasswordForm.controls).forEach(key => {
                    this.resetPasswordForm.get(key).markAsDirty();
                });
                return;
            }
        }

    }
}
