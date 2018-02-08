import { Tax } from './../models/tax';
import { HttpService } from './../shared/http.service';
import { Observable } from 'rxjs/Rx';
import { Product, KeyPair } from './../models/index';
import { Page } from './../models/page';
import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class TaxService {

  private _searchurl = '/api/tax';
  constructor(private _http: HttpService) { }

  getTaxList(): Observable<Tax[]> {
    return this._http.get(this._searchurl + "/list")
      .map((response: Response) => response.json())
  }
}
