import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { TooltipModule } from 'ngx-bootstrap';
import { CommonModule } from '@angular/common';
import { SharedModule } from './../../shared/shared.module';
// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components
import { CartViewComponent } from './cart-view.component';

@NgModule({
    imports: [
        CommonModule, TooltipModule.forRoot(), SharedModule, FormsModule, RouterModule
    ],
    declarations: [
        CartViewComponent,
    ],
    exports: [
        CartViewComponent,
    ]
})
export class CartViewModule {

}
