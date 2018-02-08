import { OrderHistoryListModule } from './order-history-list/order-history-list.module';
import { ReactiveFormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { MyDateRangePickerModule } from 'mydaterangepicker';
import { DateRangePickerComponent } from './date-range-picker/date-range-picker.component';
import { OrderFilterComponent } from './order-filter/order-filter.component';
import { TrackOrderComponent } from './track-order/track-order.component';
// Angular Imports
import { NgModule } from '@angular/core';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';

// This Module's Components
import { OrderHistoryComponent } from './order-history.component';

@NgModule({
    imports: [
        MyDateRangePickerModule, CommonModule, ReactiveFormsModule, OrderHistoryListModule, InfiniteScrollModule
    ],
    declarations: [
        OrderHistoryComponent, TrackOrderComponent, OrderFilterComponent, DateRangePickerComponent
    ],
    exports: [
        OrderHistoryComponent, TrackOrderComponent, OrderFilterComponent, DateRangePickerComponent
    ]
})
export class OrderHistoryModule {

}
