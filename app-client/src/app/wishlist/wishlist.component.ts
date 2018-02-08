import { WishListService } from './../service/wishlist.service';
import { Router } from '@angular/router';
import { Product } from './../models/product';
import { Component, Input } from '@angular/core';
import swal from 'sweetalert2';

@Component({
    moduleId: module.id,
    selector: 'app-wishlist',
    templateUrl: 'wishlist.component.html',
    styleUrls: ['wishlist.component.css'],
    providers: [WishListService]
})
export class WishlistComponent {

    @Input()
    currentItem: any;

    resp: any;
    wishListService: WishListService;

    constructor(private router: Router, private _wishListService: WishListService) {
        this.wishListService = _wishListService;
    }

    addToList(itemId) {
        if (localStorage.getItem('user') && localStorage.getItem('userToken')) {
            console.log(this.wishListService.addItemToWishList(itemId, localStorage.getItem('user')).subscribe(data => {
                if (data) {
                    swal('Success', 'Item has been added to your wishlist successfully', 'success');
                }else {
                    swal('Warning', 'Oops.. it seems you are not sign in OR your session has been expired', 'warning');
                }
            }));
        }else {
            swal('Info', 'Oops.. it seems you are not sign in OR your session has been expired', 'info');
            this.router.navigate(['/', 'login']);
        }
    }
}
