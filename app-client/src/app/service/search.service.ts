import { HttpService } from './../shared/http.service';
import { Observable } from 'rxjs/Rx';
import { Product, KeyPair } from './../models/index';
import { Page } from './../models/page';
import { Http, Response } from '@angular/http';
import { Injectable } from '@angular/core';

@Injectable()
export class SearchService {

  private _searchurl = '/api/search';
  constructor(private _http: HttpService) { }

  doSuggest(keyword: string): Observable<KeyPair[]> {
    return this._http.get(this._searchurl + "/category/" + keyword)
      .map((response: Response) => response.json())
  }
}
