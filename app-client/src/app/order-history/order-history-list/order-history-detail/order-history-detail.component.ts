import { Router, ActivatedRoute } from '@angular/router';
import { OrderItem } from './../../../models/order-item';
import { Order } from './../../../models/order';
import { UserOrderService } from './../../../service/user-orders.service';
import { Component, Input, OnInit } from '@angular/core';


@Component({
    moduleId: module.id,
    selector: 'order-history-detail',
    templateUrl: 'order-history-detail.component.html',
    styleUrls: ['order-history-detail.component.css'],
    providers: [UserOrderService]
})
export class OrderHistoryDetailComponent implements OnInit {
    private order: Order;
    private orderItems: OrderItem[];
    private currentOrderId: Number = 0;
    private userId: any;

    ngOnInit(): void {
        this.userId = localStorage.getItem('user');
        this.fetchDetails();
    }

    constructor(private userOrderService: UserOrderService, private activatedRouter: ActivatedRoute, private router: Router) { }

    fetchDetails(): void {
        this.activatedRouter.params.subscribe(param => {
            this.currentOrderId = param['orderId'];
            this.userOrderService.findOrderById(this.userId, param['orderId']).subscribe(order => {
                this.order = order[0];
                this.orderItems = this.order.orderItems;
            });
        });
    }

    routeToProductPage(itemName): void {
        itemName = itemName.split(' ').join('-');
        this.router.navigate(['/product', itemName]);
    }

    goBack(): void {
        this.router.navigate(['/orderHistory']);
    }
}
