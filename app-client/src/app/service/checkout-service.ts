/**
 * CheckoutService service
 *@author Mirza Touseef Baig
 */
import {HttpService} from './../shared/http.service';
import {User} from './../models/user';
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Observable';

@Injectable()
export class CheckoutService {

  private _url = '/api/checkout/';

  constructor(private _http: HttpService) {
  }

  /**
   *  doSingup method
   *
   * @param {User} user
   * @returns {Observable<any | Promise<any>>}
   */
  saveUser(user: User) {
    return this._http.post(this._url + 'saveuser', user).map(res => res.json());
  }

  placeOrder(obj) {
    return this._http.post(this._url + 'placeorder', obj).map(res => res.json());
  }

  getMapOfUnavailbelItems(userId: number) {
          return this._http.get(this._url + userId).map(res => res.json());
      }
}
