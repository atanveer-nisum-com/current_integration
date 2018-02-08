import { Observable } from 'rxjs';
import { HttpService } from './../shared/http.service';
import { User } from './../models/user';
import { Http, RequestOptions, Headers } from '@angular/http';
import { Injectable } from '@angular/core';

/**
 * Service to call login api
 */
@Injectable()
export class LoginService {

    /**
     * @var get login API
     */
    private _url: string = '/api/user/';

    /**
    * constructor
    * @param http
    * @see Http
    */
    constructor(private _http: HttpService) { }

    /**
     * This method login the user
     * @see Observable
     */
    doLogin(user: User): Observable<any> {

        const data = { 'emailAddress': user.emailAddress, 'password': user.password, 'rememberMe': user.rememberMe, 'guestId': '' };
        if (localStorage.getItem('user') && localStorage.getItem('user') !== 'undefined') {
            data.guestId = localStorage.getItem('user');
        } else if (localStorage.getItem('user') == 'undefined') {
            data.guestId = '0';
        }
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this._http.post(this._url + 'login', JSON.stringify(data), options)
            .map(res => res.json())
            .catch((error: any) => Observable.throw(error || 'Server error'));
    }

    getUserObject(user) {
        return this._http.post(this._url, user).map(res => res.json());
    }

}
