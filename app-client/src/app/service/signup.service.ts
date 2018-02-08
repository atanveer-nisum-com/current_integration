/**
 * SignUp service
 *@author Abdul Ghaffar Mallah
 */
import {HttpService} from './../shared/http.service';
import {User} from './../models/user';
import {Http, Headers} from '@angular/http';
import {Injectable} from '@angular/core';
import {Observable} from "rxjs/Observable";

@Injectable()
export class SignupService {

  private _url = "/api/user/signup";

  constructor(private _http: HttpService) {
  }

  /**
   *  doSingup method
   *
   * @param {User} user
   * @returns {Observable<any | Promise<any>>}
   */
  doSingnup(user: User) {
    return this._http.post(this._url, user).map(res => res.json());
  }
}
