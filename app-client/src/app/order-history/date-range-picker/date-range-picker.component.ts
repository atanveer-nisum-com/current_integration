import { Order } from './../../models/order';
import { UserOrderService } from './../../service/user-orders.service';
import { Component, OnInit, EventEmitter, Output } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
// import { IMyDrpOptions } from '../../src/my-date-range-picker/interfaces';
import { IMyDrpOptions, IMyDateRangeModel } from 'mydaterangepicker';

@Component({
    selector: 'date-range-picker',
    templateUrl: 'date-range-picker.component.html',
    styleUrls: ['date-range-picker.component.css'],
    providers: [UserOrderService]
})

export class DateRangePickerComponent implements OnInit {

    private myDateRangePickerOptions: IMyDrpOptions = {
        // other options...
        dateFormat: 'yyyy-mm-dd',
        disableSince: { year: 0, month: 0, day: 0 }
    };

    private myForm: FormGroup;

    userId;

    /**
     * @param orders to emitting response data
     */
    @Output() orders: EventEmitter<Order[]> = new EventEmitter();

    constructor(private formBuilder: FormBuilder, private userOrderService: UserOrderService) { }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            // Empty string means no initial value. Can be also specific date range for example:
            // {beginDate: {year: 2018, month: 10, day: 9}, endDate: {year: 2018, month: 10, day: 19}}
            // which sets this date range to initial value. It is also possible to set initial
            // value using the selDateRange attribute.

            myDateRange: ['', Validators.required]
            // other controls are here...
        });
        this.userId = localStorage.getItem('user');
        // this.userId = localStorage.getItem('user');
    }

    setDateRange(): void {
        // Set date range (today) using the setValue function
        const date = new Date();
        this.myForm.setValue({
            myDateRange: {
                beginDate: {
                    year: date.getFullYear(),
                    month: date.getMonth() + 1,
                    day: date.getDate()
                },
                endDate: {
                    year: date.getFullYear(),
                    month: date.getMonth() + 1,
                    day: date.getDate()
                }
            }
        });

    }

    clearDateRange(): void {
        // Clear the date range using the setValue function
        this.myForm.setValue({ myDateRange: '' });
    }

    // dateRangeChanged callback function called when the user apply the date range. This is
    // mandatory callback in this option. There are also optional inputFieldChanged and
    // calendarViewChanged callbacks.
    onDateRangeChanged(event: IMyDateRangeModel) {
        // event properties are: event.beginDate, event.endDate, event.formatted,
        // event.beginEpoc and event.endEpoc
        const dateArr = event.formatted.split(' - ');
        this.userOrderService.findOrderByDate(this.userId, dateArr[0], dateArr[1]).subscribe(data => {
            this.orders.emit(data.content);
        }, error => {
            this.orders.emit([]);
        }) ;
    }

    // Calling this function set disableSince starting from tomorrow
    disableSince() {
        const d = new Date();
        d.setDate(d.getDate() + 1);
        const copy = this.getCopyOfOptions();
        copy.disableSince = {
            year: d.getFullYear(),
            month: d.getMonth() + 1,
            day: d.getDate()
        };
        this.myDateRangePickerOptions = copy;
    }

    // Returns copy of myOptions
    getCopyOfOptions(): IMyDrpOptions {
        return JSON.parse(JSON.stringify(this.myDateRangePickerOptions));
    }
}
