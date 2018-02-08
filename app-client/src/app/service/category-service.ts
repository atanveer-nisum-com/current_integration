import { HttpService } from './../shared/http.service';
import { Page } from './../models/page';
import { Product } from './../models/product';

import { Injectable } from '@angular/core';
import { Http, Response } from '@angular/http';
import { Observable } from 'rxjs/Rx';
import { CategoryHierarchy } from './../models/index';


/**
  * Service of Category allows to get categories.
  */
@Injectable()
export class CategoryService {

  private _categoryapi = '/api/';

  /**
   * constructor of CategoryService
   * @param http
   * @see Http
   */
  constructor(private http: HttpService) {
  }

  /**
   * This method gets the hierarchy of the category with it's child categories
   * @see Observable
   * @see CategoryHierarchy
   */
  getCategoryHierarchy(): Observable<CategoryHierarchy[]> {
    return this.http.get(this._categoryapi + 'category')
      .map((res: Response) => res.json())
      .catch((error: any) => Observable.throw(error.json().error || 'Server error'));
  }

  getCategoryListById(pageNumber: number = 0, categoryId: number): Observable<Page<Product>> {
        return this.http.get(this._categoryapi + 'product/category/' + categoryId + '?&size=50&page=' + pageNumber)
            .map((response: Response) => <Page<Product>>response.json());
            //.do(data => console.log('shery-data' + JSON.stringify(data)));
    }

}
