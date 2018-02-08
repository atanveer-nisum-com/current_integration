import { Router } from '@angular/router';
import { FeaturedItemService } from './../../service/featured-item-service';
import { Product } from './../../models/product';
import { Component, OnInit, Input, OnChanges, SimpleChanges } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-product',
    templateUrl: 'product.component.html',
    styleUrls: ['product.component.css'],
    providers: [FeaturedItemService]
})
export class ProductComponent implements OnChanges {

    // itemName: string;
    itemImage: string;
    productData: Product;
    featuredItemService: FeaturedItemService;

    @Input()
    itemName: string;
    constructor(_featuredItemService: FeaturedItemService, private router: Router) {
        this.featuredItemService = _featuredItemService;
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.itemName = this.itemName.replace(/-/g, ' ');
        this.featuredItemService.getProduct(this.itemName)
        .subscribe(prodcut => {
            this.productData = prodcut;
            if (prodcut.item.itemImages.length > 0) {
                this.itemImage = prodcut.item.itemImages[0].imagePath;
            }
       });
    }
}
