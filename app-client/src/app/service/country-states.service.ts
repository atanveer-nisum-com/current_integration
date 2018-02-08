import {Injectable} from "@angular/core";
import {HttpService} from "../shared/http.service";
import {CountryState} from "../models/state";

@Injectable()
export class CountryStatesService {

  private _url = '/api/state/';

  constructor(private http: HttpService) {
  }

  getStates(countryId: number) {
    return this.http.get(this._url + countryId).map((res) => res.json());
  }

}
