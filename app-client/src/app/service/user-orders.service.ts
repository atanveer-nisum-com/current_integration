import { Page } from './../models/page';
import { Observable } from 'rxjs/Observable';
import { Order } from './../models/order';
import { HttpService } from './../shared/http.service';

import { Injectable } from '@angular/core';
import { Response, RequestOptions, Headers } from '@angular/http';




/**
  * Service of Category allows to get categories.
  */
@Injectable()
export class UserOrderService {

    /**
     * @var get order API
     */
    private _orderAPI = '/api/user/order/';

    /**
    * @constructor
    * @param http
    * @see Http
    */
    constructor(private http: HttpService) {
    }

    /**
     * This method gets the items in cart
     * @see Observable
     */
    findOrderById(userId, orderId): Observable<any> {
        return this.http.get(this._orderAPI + 'find?userId=' + userId + '&orderId=' + orderId)
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    findOrderByDate(userId, startDate, endDate): Observable<any> {
        return this.http.get(this._orderAPI + '?userId=' + userId + '&startDate=' + startDate + '&endDate=' + endDate + '&size=100')
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    cancelOrderById(userId, orderId) {
        const data = {
            'userId': userId,
            'orderId': orderId
        };

        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });
        return this.http.put(this._orderAPI + 'cancel', data)
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

}
