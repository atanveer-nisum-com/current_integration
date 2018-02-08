import { Router } from '@angular/router';
import { Product } from './../models/product';
import { FeaturedItemService } from './../service/featured-item-service';
import { Component, OnInit, Output, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-recommended-item',
    templateUrl: 'recommended-item.component.html',
    styleUrls: ['recommended-item.component.css'],
    providers: [FeaturedItemService]
})
export class RecommendedItemComponent implements OnInit {

    public myInterval: number = 5000;
    public slides: any[] = [];
    public activeSlideIndex: number;
    public noWrapSlides: boolean = false;
    private featuredItemService: FeaturedItemService;

    /**
     * @param itemName to emitting response data
     */
    @Output() itemName: EventEmitter<string> = new EventEmitter();

    public constructor(featuredItemService: FeaturedItemService, private router: Router) {
        this.featuredItemService = featuredItemService;
    }

    ngOnInit(): void {
        let products: Object[] = [];
        let counter = 0;
        this.featuredItemService.getProducts()
            .subscribe(page => {
                for (let i = 1; i < page.content.length; i++) {
                    products.push(page.content[i - 1]);
                    if (i % 3 === 0) {
                        this.slides[counter] = products;
                        products = [];
                        counter++;
                    }
                }
            });
    }

    // this function will return the name of product that contains '-' in product name
    changeName(data) {
        this.itemName.emit(data.toLowerCase().replace(/ /g, '-'));
        this.router.navigate(['/product/' + data.toLowerCase().replace(/ /g, '-')]);
    }

}
