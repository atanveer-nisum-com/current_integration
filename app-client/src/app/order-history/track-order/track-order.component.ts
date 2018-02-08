import { Observable } from 'rxjs/Observable';
import { Order } from './../../models/order';
import { UserOrderService } from './../../service/user-orders.service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

// TrackOrderComponent is in the order tracking by orderNumber feature
@Component({
    moduleId: module.id,
    selector: 'track-order',
    templateUrl: 'track-order.component.html',
    styleUrls: ['track-order.component.css'],
    providers: [UserOrderService]
})
export class TrackOrderComponent implements OnInit {
    /**
     * @param userId
     */
    userId;
    /**
     * @param orders to emitting response data
     */
    @Output() orders: EventEmitter<Order> = new EventEmitter();
    /**
     *
     * @param userOrderService
     */
    sampleOrder: Order;
    constructor(private userOrderService: UserOrderService) { }

    ngOnInit(): void {
        this.userId = localStorage.getItem('user');
    }

    /**
     *
     * @param orderId
     */
    findOrder(orderId) {
        this.userOrderService.findOrderById(this.userId, orderId).subscribe(data => {
            this.orders.emit(data);
        },
            (err) =>
                this.orders.emit(this.sampleOrder));

    }
}
