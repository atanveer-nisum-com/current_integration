import { CategoryService } from './../service/category-service';
import { Page, Product, Item } from './../models/index';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs/Observable';
import { LocalCacheRepository } from './../shared/local-cache-repository';
import { ActivatedRoute } from '@angular/router';


@Component({
    selector: 'category-list',
    templateUrl: 'category-list.component.html',
    styleUrls: ['category-list.component.css'],
    providers: [CategoryService]
})
export class CategoryListComponent implements OnInit {

    categoryTitle = 'Categories';
    pageFeaturedItems: Page<Product>;
    featuredItems: Product[];
    categoryService: CategoryService;
    data: Item;
    currentID: number;
    localCacheRepository: LocalCacheRepository<Product> = new LocalCacheRepository<Product>();


    constructor(categoryService: CategoryService, private route: ActivatedRoute) {
        this.categoryService = categoryService;
    }

    ngOnInit(): void {
        this.route.params.subscribe(params => {
            this.currentID = +params['id'];
            this.getCategory(this.currentID);
        });
        // this.route.data.subscribe(data => {
        //     this.onCategoryRetrieved(data['featuredItems']);
        // });
    }
    // onCategoryRetrieved(featuredItems: Product[]) {
    //     this.featuredItems = featuredItems;
    //     if (this.featuredItems[0].category.id === 0) {
    //             this.categoryTitle = 'Categories';
    //     } else {
    //         this.categoryTitle = '${this.featuredItems[0].category.name}';
    //     }
    // }
    getCategory(id: number): void {
        this.categoryService.getCategoryListById(0, id)
            .subscribe(page => {
                if (page.content.length != 0) {
                    this.pageFeaturedItems = page;
                    this.localCacheRepository.cacheData(page.content);
                    this.featuredItems = this.localCacheRepository.next();
                    this.categoryTitle = page.content[0].category.name;
                } else {
                    this.featuredItems = [];
                    this.localCacheRepository.clear();
                    this.categoryTitle = 'No record is found';
                }
            });
    }

    changeName(data) {
        return data.toLowerCase().replace(/ /g, '-');
    }

    onScroll() {

        if (this.localCacheRepository.haveEnoughData) {
            this.featuredItems = this.featuredItems.concat(this.localCacheRepository.next());
        } else {
            if (this.pageFeaturedItems != null && !this.pageFeaturedItems.last) {
                this.categoryService.getCategoryListById(this.pageFeaturedItems.number + 1, this.currentID).subscribe(page => {
                    this.localCacheRepository.cacheData(page.content);
                    this.featuredItems = this.featuredItems.concat(this.localCacheRepository.next());
                    this.pageFeaturedItems = page;
                });
            }
        }
    }
}

