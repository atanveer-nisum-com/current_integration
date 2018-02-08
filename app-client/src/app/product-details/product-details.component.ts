import { Http } from '@angular/http';
import { Router } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';

@Component({
    moduleId: module.id,
    selector: 'app-product-details',
    templateUrl: 'product-details.component.html',
    styleUrls: ['product-details.component.css']
})
export class ProductDetailsComponent implements OnInit {

    // itemName of current porduct
    itemName: string;

    constructor(private router: Router) {
    }

    /**
     * this init function fetch the itemName from url
     */
    ngOnInit(): void {

        const urlArr = this.router.url.split('/');
        this.itemName = urlArr[2].replace(/-/g, ' ');
    }

    /**
     * @param data
     * this function change the itemName for product component
     */
    changeName(data) {
        this.itemName = data;
    }

}
