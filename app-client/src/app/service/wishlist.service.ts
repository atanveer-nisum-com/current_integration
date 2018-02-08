import { Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import { HttpService } from './../shared/http.service';
import { Injectable } from '@angular/core';

@Injectable()
export class WishListService {

    private wishListUrl = '/api/wishlist/';

    constructor(private _http: HttpService) { }

    addItemToWishList(itemId: number, userId: any): Observable<any> {
        // tslint:disable-next-line:radix
        return this._http.get(this.wishListUrl + 'add?wishlistid=0&itemid=' + itemId + '&userid=' + parseInt(userId))
            .map((response: Response) => response.json());
    }

    getWishList(userId: any): Observable<any> {
        // tslint:disable-next-line:radix
        return this._http.get(this.wishListUrl + parseInt(userId))
            .map((response: Response) => response.json());
    }

    removeFromList(wishlistItemId: number, userId: any): Observable<any> {
        return this._http.get(this.wishListUrl + 'remove?wishlistid=0&wishlistitemid=' + wishlistItemId + '&userid=' + parseInt(userId))
            .map((response: Response) => response.json());
    }
}
