import { Injectable } from '@angular/core';
import { Http, RequestOptions, Headers } from '@angular/http';
import { HttpService } from './../shared/http.service';
import { Observable } from 'rxjs/Rx';


@Injectable()
export class StoresService {

  _url: string = '/api/store/';

  constructor(private _http: HttpService) { }

  getStores(zipcode: number = 0): Observable<any> {
    return this._http.get(this._url + "zipcode/" + zipcode)
      .map(response => response.json())
      .catch((error: any) => Observable.throw(error || 'Server error'));
  }


  getSelectedStore(userId: number = 0): Observable<any> {
    return this._http.get(this._url + 'user/' + userId)
      .map(response => response.json())
      .catch((error: any) => Observable.throw(error || 'Server error'));
  }

  postStore(storeId: number, userId: number): Observable<any> {
    return this._http.post(this._url + '?userId=' + userId + '&storeId=' + storeId, '')
      .map(res => res.json())
      .catch((error: any) => Observable.throw(error || 'Server error'));
  }

}
