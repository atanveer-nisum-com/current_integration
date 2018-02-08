import { HttpService } from './../shared/http.service';
import { Page } from './../models/page';
import { Product } from './../models/index';
import { Injectable } from '@angular/core';
import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class SearchItemService {

    private _producturl = '/api/search/product/';

    constructor(private _http: HttpService) { }

    // tslint:disable-next-line:no-unused-expression
    getSearchItems(pageNumber: number = 0): Observable<Page<Product>> {
        return this._http.get(this._producturl + 'GG' + '?page=' + pageNumber)
            .map((response: Response) => <Page<Product>> response.json())
            ;
    }

     getSku(keyword: string = 'sku6' ,pageNumber: number = 0): Observable<Page<Product>> {
        return this._http.get(this._producturl + 'sku/'+ keyword + '?page=' + pageNumber)
            .map((response: Response) => <Page<Product>> response.json())
            ;
    }
}
