import {Injectable} from '@angular/core';
import {HttpService} from '../shared/http.service';
import {Address} from '../models/address';
import {Observable} from 'rxjs/Observable';
import {Page} from '../models/page';
import {RequestOptions, Headers} from "@angular/http";

@Injectable()
export class AddressBookService {
  _url = '/api/addressbook';

  constructor(private _http: HttpService) {
  }

  addNewShippingAddress(shippingAddress: Address): Observable<Page<Address>> {
    const headers = new Headers({'Content-Type': 'application/json'});
    const options = new RequestOptions({headers: headers});
    return this._http.post(this._url + '/create', JSON.stringify(shippingAddress), options).map(response => response.json());
  }

  deleteShippingAddress(shippingAddressId: number): Observable<Page<Address>> {
    return this._http.delete(this._url + '?addressId=' + shippingAddressId).map(response => response.json());
  }

  getAllShippingAddresses(userId: number): Observable<Page<Address>> {
    return this._http.get(this._url + '?userId=' + userId).map(response => response.json());
  }

  updateShippingAddress(updatedShippingAddress: Address): Observable<Page<Address>> {
    const headers = new Headers({'Content-Type': 'application/json'});
    const options = new RequestOptions({headers: headers});
    return this._http.put(this._url + '/update', JSON.stringify(updatedShippingAddress), options).map(response => response.json());
  }
}
