import { HttpService } from './../shared/http.service';
import { User } from './../models/user';
import { Http, RequestOptions,Headers } from '@angular/http';
import { Injectable } from '@angular/core';

/**
  * Service of forget password.
  */
@Injectable()
export class ResetPasswordService {
    /**
        * @var API of forget password
        */
    private _url = '/api/user/resetPassword';
    /**
        * @constructor
        * @param http
        * @see Http
        */
    constructor(private _http: HttpService) { }

    /**
     * This method change the password of the user
     * @see Observable
     */
    changePassword(token: string, password: string, confirmPassword: string) {

        const data = { 'resetToken': token, 'password': password, 'confirmPassword': confirmPassword };

        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        return this._http.post(this._url, JSON.stringify(data), options).map(res => res.json());
    }


    isResetPasswordLinkValid(link) {
        return this._http.get(this._url + '/' + link).map(res => res.json());
    }
}
