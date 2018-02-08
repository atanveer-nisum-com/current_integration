import { LocalCacheRepository } from './../shared/local-cache-repository';
import { Page, Product } from './../models/index';
import { SearchItemService } from './../service/search-item-service';
import { Component, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-search-item',
    templateUrl: 'search-item.component.html',
    styleUrls: ['search-item.component.css'],
    providers: [SearchItemService]
})
export class SearchItemComponent implements OnInit {

    localCacheRepository: LocalCacheRepository<Product> = new LocalCacheRepository<Product>();


    pagesearchItems: Page<Product>;
    searchItems: Product[];
    searchItemService: SearchItemService;
    itemName = 'GG';

    constructor(searchItemService: SearchItemService) {
        this.searchItemService = searchItemService;
    }

    ngOnInit(): void {
        this.searchItemService.getSearchItems().
            subscribe(page => {
                                this.pagesearchItems = page;
                                    this.localCacheRepository.cacheData(page.content);
                                    this.searchItems = this.localCacheRepository.next();
                            });
    }

    onScroll() {


    if ( this.localCacheRepository.haveEnoughData ) {
            this.searchItems = this.searchItems.concat(this.localCacheRepository.next());
        }else {
            if (this.pagesearchItems != null && !this.pagesearchItems.last) {
                this.searchItemService.getSearchItems(this.pagesearchItems.number + 1).subscribe(page => {

                    this.localCacheRepository.cacheData(page.content);
                    this.searchItems = this.searchItems.concat(this.localCacheRepository.next());
                    this.pagesearchItems = page;
                });
            }
        }
    }

    onItem(prod) {
        console.log('Hello :: ' + prod.name);
    }

}
