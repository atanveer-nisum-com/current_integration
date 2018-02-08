import { UserOrderService } from './../../service/user-orders.service';
import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import swal from 'sweetalert2';

// OrderHistoryListComponent used to display order
@Component({
    moduleId: module.id,
    selector: 'order-history-list',
    templateUrl: 'order-history-list.component.html',
    styleUrls: ['order-history-list.component.css'],
    providers: [UserOrderService]
})
export class OrderHistoryListComponent implements OnInit {

    /**
     * @param order
     */
    @Input() order;

    /**
    * @param userId
    */
    userId;
    paymentType = false;
    isDisplay = true;
    constructor(private userOrderService: UserOrderService, private router: Router) { }

    ngOnInit() {
        this.userId = localStorage.getItem('user');
        if (this.order.orderStatus === 1 || this.order.orderStatus === 3 || this.order.orderStatus === 7) {
            this.isDisplay = false;
        }
        if (this.order.transaction.paymentType.name === 'Paypal') {
            this.paymentType = true;
        }
    }

    cancelOrder(orderId) {
        swal({
            title: 'Cancel Order',
            text: 'Are you sure you want to cancel the order?',
            type: 'question',
            showCancelButton: true,
            confirmButtonText: 'Yes',
            cancelButtonText: 'No'
        }).then(() => {
            this.userOrderService.cancelOrderById(this.userId, orderId).subscribe((data) => {
                if (data) {
                    this.isDisplay = false;
                    this.order = data;
                    // swal('Cancelled', 'Your order has been cancelled.', 'success');
                }
            });
          }, function(dismiss) {
            // dismiss can be 'overlay', 'cancel', 'close', 'esc', 'timer'
            // handle dialog
          });
    }
}
