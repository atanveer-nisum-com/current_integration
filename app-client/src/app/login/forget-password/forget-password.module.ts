import { RouterModule } from '@angular/router';
import { ForgetPasswordFormModule } from './forget-password-form/forget-password-form.module';
import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
// This Module's Components
import { ForgetPasswordComponent } from './forget-password.component';

@NgModule({
    imports: [
        CommonModule, ReactiveFormsModule, ForgetPasswordFormModule, RouterModule,
    ],
    declarations: [
        ForgetPasswordComponent,
    ],
    exports: [
        ForgetPasswordComponent,
    ]
})
export class ForgetPasswordModule {

}
