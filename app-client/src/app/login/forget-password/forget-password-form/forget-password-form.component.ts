import { ForgetPasswordSuccessComponent } from './../forget-password-success/forget-password-success.component';
import { EMAIL_REGEX } from './../../../shared/utility';
import { Router } from '@angular/router';
import { LoginService } from './../../../service/login.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { ForgetPasswordService } from './../../../service/forget-password.service';
import { FormsModule } from '@angular/forms';

/**
 * Base element to create forget password component
 */
@Component({
    moduleId: module.id,
    selector: 'app-forget-password-form',
    templateUrl: 'forget-password-form.component.html',
    styleUrls: ['forget-password-form.component.css'],
    providers: [ForgetPasswordService]
})
export class ForgetPasswordFormComponent implements OnInit {
    /** create formgroup instance */
    forgetPassForm: FormGroup;
    error = false;
    message;
    successBlock = false;
    constructor(private fb: FormBuilder, private forgetPassService: ForgetPasswordService, private router: Router) { }
    ngOnInit(): void {
        this.createForgetPassForm();
    }
    /**
     * create form for forget password
     *
     */
    createForgetPassForm() {
        this.forgetPassForm = this.fb.group({
            email: ['', [Validators.required, Validators.pattern(EMAIL_REGEX)]]
        });

        this.forgetPassForm.valueChanges.debounceTime(800)
            .subscribe(data => this.onValueChanged(data));

        this.onValueChanged();
    }
    /**
     * validating the form fields
     * @param data
     */
    onValueChanged(data?: any) {
        if (!this.forgetPassForm) { return; }
        const form = this.forgetPassForm;


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
    // tslint:disable-next-line:member-ordering
    formErrors = {
        'email': ''
    };
    // tslint:disable-next-line:member-ordering
    validationMessages = {
        'email': {
            'required': 'Email address is required.',
            'pattern': 'Invalid email address',
        }
    };
    /**
     * this method is used for validating and returning response from api
     */
    onSubmitEmail() {
        if (this.forgetPassForm.valid) {
            this.forgetPassService.sendForgetEmail(this.forgetPassForm.value).subscribe(data => {
                console.log(data.status);
                if (data.status == 'success') {
                    //   swal('Success', 'Successfully Registered.', 'success');
                    this.successBlock = true;
                    this.message = data.message;
                } if (data.status == 'error') {
                    this.error = true;
                    this.message = data.message;
                }else {
                    //   swal('OOps..', 'Successfully Registered.', 'error');
                    this.message = data.message;
                }
            });
        } else {
            this.forgetPassForm.updateValueAndValidity();
            if (this.forgetPassForm.invalid) {
                Object.keys(this.forgetPassForm.controls).forEach(key => {
                    this.forgetPassForm.get(key).markAsDirty();
                });
                return;
            }
        }
    }
}
