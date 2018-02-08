import { Profile } from './../models/profile';

import { HttpService } from './../shared/http.service';
import { Product, Page } from './../models/index';
import { Injectable } from '@angular/core';
import { Http , Response ,RequestOptions, Headers} from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class UserProfileService {

    private _userProfileurl = '/api/profile/';

    constructor(private _http: HttpService) { }

    getUserProfile(userId: number): Observable<Profile> {
      
        return this._http.get(this._userProfileurl + '?userid=' + userId)
            .map((response: Response) => <Profile> response.json())
           
    }
    

    updateUserProfile(user: Profile) {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });
        return this._http.put(this._userProfileurl + 'update', JSON.stringify(user), {headers: headers})
        .map(res => res.json());
      }
}
