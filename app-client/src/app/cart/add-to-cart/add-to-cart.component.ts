/**
 *  Add To Cart Component
 * 
 * @author Abdul Ghaffar Mallah
 */
import { CART_ADD_ITEM } from './../../state-management/action/cart.action';
import { Store } from '@ngrx/store';
import { AppState } from './../../state-management/state/app.state';
import { Component, Input } from '@angular/core';

@Component({
    selector:"app-add-to-cart",
    template:`<a class="btn btn-default add-to-cart" (click)="onItemClick()" ><i class="fa fa-shopping-cart"></i>{{caption}}</a>`
})
export class AddToCartComponent {

    /**
     * Current item id
     */
    @Input("itemid") itemId;
    @Input() caption = 'Add to cart';

    constructor(private store: Store<AppState>){}

    /**
     * Triggered when clicked on add to cart button.
     * This method dispatched the following cart Action from store.
     * 
     * { type: CART_ADD_ITEM, payload: itemId }
     */
    onItemClick() {
        this.store.dispatch({ type: CART_ADD_ITEM, payload: this.itemId });
    }

}