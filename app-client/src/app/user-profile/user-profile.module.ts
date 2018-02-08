import { UserProfileComponent } from './user-profile.component';
// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components

import { ReactiveFormsModule } from '@angular/forms';


@NgModule({
    imports: [
        ReactiveFormsModule
    ],
    declarations: [
        
        UserProfileComponent
    ],
    exports: [
        UserProfileComponent,
    ]
})
export class UserProfileModule {

}
