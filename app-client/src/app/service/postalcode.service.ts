import {Injectable} from "@angular/core";
import {HttpService} from "../shared/http.service";
import {Http} from "@angular/http";

@Injectable()
export class PostalCodeService {

  private _url = 'http://localhost:8080/api/state/';

  constructor(public http: Http) {
  }

// {zipcode}/{city}/{state}/{country}
  public isPostalCodeValid(postalCode: string, city: string, state: string, country: string) {
    const params = 'validateZip/' + postalCode + '/' + city + '/' + state + '/' + country;
    console.log(this._url + params);
    return this.http.get(this._url + params);
  }

  // public isPostalCodeValid(postalCode: string, city: string, stateId: number, countryId: number): boolean {
  //   return false;
  // }
}
