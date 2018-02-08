import { Observable } from 'rxjs';
import { Order } from './../models/order';
import { HttpService } from './../shared/http.service';

import { Injectable } from '@angular/core';
import { Response } from '@angular/http';




/**
  * Service of Category allows to get categories.
  */
@Injectable()
export class ViewCartService {

    /**
     * @var get cart items API
     */
    private _viewCartapi = '/api/viewcart/';

    /**
     * @var API of CRUD operations
     */
    private _CRUDItem = '/api/order/';

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
    getCart(userId): Observable<Order> {
        if (userId != null && userId > 0) {
            return this.http.get(this._viewCartapi + userId)
                .map((res: Response) => res.json() ? res.json() : {})
                .catch((error: any) => Observable.throw(error || 'Server error'));
        } else { return; }
    }

    /**
     * Delete the item from cart
     * @param userId
     * @param itemId
     */
    deleteItem(userId, itemId): Observable<Order> {
        return this.http.delete(this._CRUDItem + 'item?orderitemid=' + itemId)
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    /**
     * Updates the item in cart
     * @param userId
     * @param itemId
     * @param quantity
     */
    updateItemQuantity(userId, itemId, quantity): Observable<Order> {
        return this.http.put(this._CRUDItem + 'item?orderitemid=' + itemId + '&quantity=' + quantity, {})
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    /**
     * Updating Order with tax.
     * @param orderId 
     * @param taxAbbrv 
     */
    updateOrderTax(orderId, taxAbbrv): Observable<any> {
        return this.http.put(this._CRUDItem + 'tax?orderid=' + orderId + '&abbrv=' + taxAbbrv, {})
            .map((res: Response) => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }
}
