import { Observable } from 'rxjs/Observable';
import { Product } from './../models/product';
import { Page } from './../models/page';
import { CategoryService } from './category-service';
import { Injectable } from '@angular/core';
import { Router, Resolve, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

@Injectable()
export class CategoryResolver implements Resolve<Page<Product>>{

    constructor(private categoryService: CategoryService,
        private router: Router) { }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Page<Product>> {
        let id = route.params['id'];
        // let id = route.paramMap.get('id');
        if (isNaN(+id)) {
            console.log(`category id was not a number: ${id}`);
            this.router.navigate(['/home']);
            return Observable.of(null);
        }
        return this.categoryService.getCategoryListById(0, +id)
            .map(response => {
                if (response) {
                    return <Page<Product>>response;
                }
                console.log(`Category was not found: ${id}`);
                this.router.navigate(['/home']);
                return null;
            })
            .catch(error => {
                console.log(`Retrieval error: ${error}`);
                this.router.navigate(['/home']);
                return Observable.of(null);
            });
    }
}
