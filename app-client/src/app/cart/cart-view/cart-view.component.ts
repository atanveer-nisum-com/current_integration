import { Response } from '@angular/http';
import swal from 'sweetalert2';
import { CheckoutService } from './../../service/checkout-service';
import { Tax } from './../../models/tax';
import { TaxService } from './../../service/tax-service';

import { Router } from '@angular/router';
import { CART_REMOVE_ITEM } from './../../state-management/action/cart.action';
import { AppState } from './../../state-management/state/app.state';
import { Store } from '@ngrx/store';
import { ItemPrice } from './../../models/itemprice';
import { OrderItem } from './../../models/order-item';

import { Order } from './../../models/order';
import { ViewCartService } from './../../service/cart-view.service';
import { Component, OnInit, Input, Output } from '@angular/core';


@Component({
    moduleId: module.id,
    selector: 'cart-view',
    templateUrl: 'cart-view.component.html',
    styleUrls: ['cart-view.component.css'],
    providers: [ViewCartService, TaxService, CheckoutService]
})
export class CartViewComponent implements OnInit {

    order: Order = null;
    userId;

    @Input()
    subTotalPrice = 0;
    @Input()
    taxation: any = 0;
    @Input()
    orderTotalPrice: any = 0;

    taxList: any[];
    selectedTax: any;

    @Input()
    isDisplay = false;

    constructor(private router: Router, private viewCartService: ViewCartService, private checkoutService: CheckoutService,
        private store: Store<AppState>, private taxService: TaxService) { }
    /**
     * Call categoryService api and save response in categories
     */
    ngOnInit(): void {
        this.userId = localStorage.getItem('user');

        if (localStorage.getItem('isDisplay') === 'hide') {
            this.isDisplay = true;
            localStorage.setItem('isDisplay', 'show');
        }

        if (this.userId != null) {
            this.viewCartService.getCart(this.userId).subscribe((order: Order) => {
                this.order = order;
                this.subTotalPrice = order.subTotalPrice;
                if (this.isDisplay) {
                    this.checkoutService.getMapOfUnavailbelItems(this.userId).subscribe(res => {
                        for (let i = 0; i < order.orderItems.length; i++) {
                            if (res.mapOfUnavailableItems[order.orderItems[i].id] !== undefined) {
                                if (order.orderItems[i].id === res.mapOfUnavailableItems[order.orderItems[i].id].id) {
                                    if (res.mapOfUnavailableItems[order.orderItems[i].id].quantity <= 0) {
                                        alert('Sorry we are just Out of Stock For this item : ' + order.orderItems[i].item.name);
                                        this.remove(order.orderItems[i].item.id);
                                    } else {
                                        alert('Sorry we have only ' + order.orderItems[i].quantity + 'items availble right now : ');
                                        this.decrement(order.orderItems[i]);
                                    }
                                }
                            }
                        }
                    });
                }
                //        this.taxation = order.taxation;
                //        this.orderTotalPrice = order.orderTotalPrice;
                this.getTaxList();
                // this.getTotalPrice(order);
            },
                err => {

                });
        }
    }

    increment(item) {
        item.quantity++;
        if (!this.updateItem(item.id, item.quantity)) {
            item.quantity--;
        }
    }
    decrement(item) {
        item.quantity--;
        if (item.quantity > 0) {
            if (!this.updateItem(item.id, item.quantity)) {
                item.quantity++;
            }
        } else { item.quantity = 1; }
    }
    updateItem(itemId, quantity) {
        this.viewCartService.updateItemQuantity(this.userId, itemId, quantity).subscribe(
            (order: Order) => {
                this.order = order;
                this.subTotalPrice = order.subTotalPrice;
                this.onTaxChange(this.selectedTax);
                // this.taxation = order.taxation;
                // this.orderTotalPrice = order.orderTotalPrice;
                this.store.dispatch({ type: CART_REMOVE_ITEM, payload: order.totalItem });
                return true;
            },
            (error: Response) => {
                swal('Out of Stock', error.json().error, 'error');
                return false;
            }
        );
    }

    remove(itemId) {
        this.viewCartService.deleteItem(this.userId, itemId).subscribe((order: Order) => {
            this.order = order;
            this.subTotalPrice = order.subTotalPrice;
            this.onTaxChange(this.selectedTax);
            //         this.taxation = order.taxation;
            //         this.orderTotalPrice = order.orderTotalPrice;
            this.store.dispatch({ type: CART_REMOVE_ITEM, payload: order.totalItem });
        });
    }


    routeToDetail(name) {
        this.router.navigate(['/product', name.replace(/[\s]/g, '-')]);
    }
    getTaxList() {
        this.taxService.getTaxList().subscribe((taxList: Tax[]) => {
            this.taxList = taxList;
            this.selectedTax = taxList[0];
            this.onTaxChange(this.selectedTax);
        });
    }

    onTaxChange(newTax: Tax) {

        this.taxation = (this.subTotalPrice * (newTax.percentage / 100));
        this.orderTotalPrice = this.subTotalPrice + this.taxation;
        this.taxation = this.taxation.toFixed(2);
        this.orderTotalPrice = this.orderTotalPrice.toFixed(2);
    }

    onStageCheckout() {
        this.viewCartService.updateOrderTax(this.order.id, this.selectedTax.abbrv).subscribe((checker: boolean) => {
            this.router.navigate(['checkout']);
        });
    }

}
