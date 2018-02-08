import { RouterModule } from '@angular/router';
// import { OrderHistoryDetailComponent } from './order-history-detail/order-history-detail.component';
// Angular Imports
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
// This Module's Components
import { OrderHistoryListComponent } from './order-history-list.component';

@NgModule({
    imports: [
        CommonModule,
        RouterModule,
    ],
    declarations: [
        OrderHistoryListComponent
    ],
    exports: [
        OrderHistoryListComponent
    ]
})
export class OrderHistoryListModule {

}
