import { UserOrderService } from './../../service/user-orders.service';
import { Order } from './../../models/order';
import { Component, Output, EventEmitter, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'order-filter',
    templateUrl: 'order-filter.component.html',
    styleUrls: ['order-filter.component.css'],
    providers: [UserOrderService]
})
export class OrderFilterComponent implements OnInit {

    viewOptions = ['orders placed within last 7 days', 'orders placed within last 60 days',
        'orders placed within last 6 months', 'orders placed within current year'];

    userId;

    /**
     * @param orders to emitting response data
     */
    @Output() orders: EventEmitter<Order[]> = new EventEmitter();

    constructor(private userOrderService: UserOrderService) { }

    ngOnInit() {
        this.userId = localStorage.getItem('user');
        let d = new Date();
        d.setDate(d.getDate() - 7);
        this.userOrderService.findOrderByDate(this.userId, this.formatDate(d), this.formatDate(new Date())).subscribe(data => {
            this.orders.emit(data.content);
        });
    }

    onOptionChanged(selectedValue) {
        let d = new Date();

        switch (selectedValue) {
            case '0':
                {
                    d.setDate(d.getDate() - 7);
                    this.userOrderService.findOrderByDate(this.userId, this.formatDate(d), this.formatDate(new Date())).subscribe(data => {
                        this.orders.emit(data.content);
                    });
                    break;
                }
            case '1':
                {
                    d.setDate(d.getDate() - 60);
                    this.userOrderService.findOrderByDate(this.userId, this.formatDate(d), this.formatDate(new Date())).subscribe(data => {
                        this.orders.emit(data.content);
                    });
                    break;
                }
            case '2':
                {
                    d.setDate(d.getDate() - 182.5);
                    console.log(this.formatDate(new Date()) + ' : ' + this.formatDate(d));
                    this.userOrderService.findOrderByDate(this.userId, this.formatDate(d), this.formatDate(new Date())).subscribe(data => {
                        this.orders.emit(data.content);
                    });
                    break;
                }
            case '3':
                {
                    d.setDate(d.getDate() - 365);
                    console.log(this.formatDate(new Date()) + ' : ' + this.formatDate(d));
                    this.userOrderService.findOrderByDate(this.userId, this.formatDate(d), this.formatDate(new Date())).subscribe(data => {
                        this.orders.emit(data.content);
                    });
                    break;
                }

        }
    }

    formatDate(date): string {
        return date.toISOString().substring(0, 10);
    }
}
