import { HttpService } from './../shared/http.service';
import { Product, Page } from './../models/index';
import { Injectable } from '@angular/core';
import { Http , Response } from '@angular/http';
import { Observable } from 'rxjs/Observable';
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/do';

@Injectable()
export class FeaturedItemService {

    private _producturl = '/api/product';

    constructor(private _http: HttpService) { }

    getProducts(pageNumber: number = 0, totalProducts: number = 50): Observable<Page<Product>> {
        return this._http.get(this._producturl + '?size=' + totalProducts + '&page=' + pageNumber)
            .map((response: Response) => <Page<Product>>response.json());
           // .do(data => console.log(JSON.stringify(data)));
    }

    getProduct(itemName: string): Observable<Product> {
        return this._http.get(this._producturl + '/name/' + itemName)
            .map((response: Response) => <Product> response.json());
           // .do(data => console.log(JSON.stringify(data)));
    }
}
