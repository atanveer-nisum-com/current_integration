import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components
import { ResetPasswordComponent } from './reset-password.component';

@NgModule({
    imports: [
        ReactiveFormsModule, CommonModule
    ],
    declarations: [
        ResetPasswordComponent,
    ],
    exports: [
        ResetPasswordComponent,
    ]
})
export class ResetPasswordModule {

}
