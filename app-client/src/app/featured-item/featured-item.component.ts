import { CART_ADD_ITEM } from './../state-management/action/cart.action';
import { AppState } from './../state-management/state/app.state';
import { Router } from '@angular/router';
import { Page, Product, Item } from './../models/index';
import { Component, ViewChild, ElementRef, OnInit, Output, EventEmitter } from '@angular/core';
import { FeaturedItemService } from '../service/featured-item-service';
import { Observable } from 'rxjs/Observable';
import { LocalCacheRepository } from './../shared/local-cache-repository';

import { Store } from '@ngrx/store';

@Component({
    selector: 'app-featured-item',
    templateUrl: 'featured-item.component.html',
    styleUrls: ['featured-item.component.css'],
    providers: [FeaturedItemService]
})
export class FeaturedItemComponent implements OnInit {

    router: Router;
    itemName: string;
    pageFeaturedItems: Page<Product>;
    featuredItems: Product[];
    featuredItemService: FeaturedItemService;
    data: Item;

    localCacheRepository: LocalCacheRepository<Product> = new LocalCacheRepository<Product>();

    constructor(_router: Router, featuredItemService: FeaturedItemService, private store: Store<AppState>) {
        this.router = _router;
        this.featuredItemService = featuredItemService;
    }

    ngOnInit(): void {
        this.featuredItemService.getProducts()
            .subscribe(page => {
                this.pageFeaturedItems = page;
                this.localCacheRepository.cacheData(page.content);

                this.featuredItems = this.localCacheRepository.next();
            });
    }

    // this function will return the name of product that contains '-' in product name
    changeName(data) {
        return data.toLowerCase().replace(/ /g, '-');
    }

    onScroll() {

        if(!this.featuredItems) {
            return;
        }

        if (this.localCacheRepository.haveEnoughData) {
            this.featuredItems = this.featuredItems.concat(this.localCacheRepository.next());
        } else {
            if (this.pageFeaturedItems != null && !this.pageFeaturedItems.last) {
                this.featuredItemService.getProducts(this.pageFeaturedItems.number + 1).subscribe(page => {

                    this.localCacheRepository.cacheData(page.content);
                    this.featuredItems = this.featuredItems.concat(this.localCacheRepository.next());
                    this.pageFeaturedItems = page;
                });
            }
        }
    }
}
