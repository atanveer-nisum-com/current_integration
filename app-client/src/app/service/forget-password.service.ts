import { HttpService } from './../shared/http.service';
import { User } from './../models/user';
import { Http } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class ForgetPasswordService {

    private _url = '/api/user/forgetPassword';

    constructor(private _http: HttpService) { }

    sendForgetEmail(email) {
        return this._http.post(this._url, email, null, true).map(res => res.json());
    }
}
