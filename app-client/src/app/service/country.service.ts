import {Injectable} from '@angular/core';
import {HttpService} from "../shared/http.service";

@Injectable()
export class CountryService {

  _url = '/api/country';

  constructor(private http: HttpService) {
  }

  getCountriesList() {
    return this.http.get(this._url).map(res => res.json());
  }
}
