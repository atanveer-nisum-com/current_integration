import { RequestOptions, Headers } from '@angular/http';
import { Observable } from 'rxjs';

/**
 * CartService
 * This Service is responsible to bind back-end rest cart api calls.
 * 
 * @author Abdul Ghaffar Mallah
 */
import { Injectable } from '@angular/core';
import { HttpService } from './../shared/http.service';

@Injectable()
export class CartService {

    /**
     * URI of add to cart resource
     */
    private _url = "/api/order/item";

    constructor(private _http: HttpService) { }

    /**
     *  Adds Item to back-end.
     * 
     * @param {number} userId id of user, default will be 0
     * @param {number} itemId id of item to add in the cart
     */
    addItem(userId: number = 0, itemId: number) {
        //let formData = new FormData();
        let data = { 'userId': '', 'itemId': '' };
        if (userId > 0) {
            //   formData.append('userid', userId + '');
            data.userId = userId + '';
        }
        //formData.append('itemid', itemId + "");
        data.itemId = itemId + '';

        return this._http.post(this._url, JSON.stringify(data))
            .map(res => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }
}