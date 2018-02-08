// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components
import { CheckoutComponent } from './checkout.component';

@NgModule({
    declarations: [
        CheckoutComponent,
    ],
    exports: [
        CheckoutComponent,
    ]
})
export class CheckoutModule {

}
