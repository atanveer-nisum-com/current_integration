import { LocalCacheRepository } from './../shared/local-cache-repository';
import { UserState } from './../state-management/state/user.state';
import { Router } from '@angular/router';
import { AppState } from './../state-management/state/app.state';
import { Order } from './../models/order';
import { Observable } from 'rxjs/Observable';
import { Component, OnInit, Directive } from '@angular/core';

// OrderHistoryComponent is int the order finding/tracking feature
@Component({
    moduleId: module.id,
    selector: 'order-history',
    templateUrl: 'order-history.component.html',
    styleUrls: ['order-history.component.css']
})
export class OrderHistoryComponent {
    constructor(private router: Router) { }
    userState = 'Login';
    orders: Order[];
    currentPage: number = 0;
    localCacheRepository: LocalCacheRepository<Order> = new LocalCacheRepository<Order>();

    /**
     *
     * @param data
     */
    getOrders(data) {
        this.localCacheRepository.clear();
        if (data) {
            this.localCacheRepository.cacheData(data);
            this.orders = this.localCacheRepository.next();
        } else {
            this.orders = [];
        }
    }

    ngOnInit(): void {
        if (localStorage.getItem('isLoged') == 'false') {
            this.router.navigateByUrl('/login');
            localStorage.setItem('oldstate', this.router.url);
        }
    }

    onScroll(): void {
        if (this.localCacheRepository.haveEnoughData) {
            this.orders = this.orders.concat(this.localCacheRepository.next());
        }
    }
}
