import { RouterModule } from '@angular/router';
import { ForgetPasswordSuccessComponent } from './../forget-password-success/forget-password-success.component';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components
import { ForgetPasswordFormComponent } from './forget-password-form.component';

@NgModule({
    imports: [
        ReactiveFormsModule, CommonModule, RouterModule
    ],
    declarations: [
        ForgetPasswordFormComponent, ForgetPasswordSuccessComponent
    ],
    exports: [
        ForgetPasswordFormComponent, ForgetPasswordSuccessComponent
    ]
})
export class ForgetPasswordFormModule {

}
