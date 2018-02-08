import { WishlistItem } from './../models/wishlist-item';
import { Page } from './../models/page';
import { Wishlist } from './../models/wishlist';
import { Router } from '@angular/router';
import { WishListService } from './../service/wishlist.service';
import { Component, OnInit } from '@angular/core';
import swal from 'sweetalert2';
@Component({
  selector: 'app-app-wishlist',
  templateUrl: './app-wishlist.component.html',
  styleUrls: ['./app-wishlist.component.css'],
  providers: [WishListService]
})

export class AppWishlistComponent implements OnInit {

  constructor(private wishListService: WishListService, private router: Router) {

  }

  private wishListData: Wishlist[];
  private pageWishlist: Page<Wishlist>;
  private wishlistItems: WishlistItem[];


  ngOnInit() {
    if (localStorage.getItem('user')) {
      this.wishListService.getWishList(localStorage.getItem('user')).subscribe(data => {
        this.pageWishlist = data;
        this.wishListData = data.content;
        if (this.wishListData.length > 0) {
          this.wishlistItems = data.content[0].wishlistItems;
        }
      });
    } else {
      swal('Info', 'Oops.. it seems you are not sign in OR your session has been expired', 'info');
      this.router.navigate(['/', 'login']);
    }
  }

  removeFromList(wishlistItem: WishlistItem) {

    this.wishListService.removeFromList(wishlistItem.id, localStorage.getItem('user'))
      .subscribe(data => {
        if (data) {
          this.wishlistItems = this.wishlistItems.filter(item => item.id !== wishlistItem.id);
        }
      });
  }
  // this function will return the name of product that contains '-' in product name
  changeName(data) {
    return data.toLowerCase().replace(/ /g, '-');
  }
}
