// import { AddToCartComponent } from './../../../cart/add-to-cart/add-to-cart.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OrderHistoryDetailComponent } from './order-history-detail.component';

@NgModule({
    imports: [
        CommonModule,
    ],
    declarations: [
        OrderHistoryDetailComponent
    ],
    exports: [
        OrderHistoryDetailComponent
    ]
})
export class OrderHistoryDetailModule {

}
