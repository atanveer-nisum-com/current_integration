import { AppWishlistComponent } from './wishlist/app-wishlist.component';
import { OrderHistoryDetailComponent } from './order-history/order-history-list/order-history-detail/order-history-detail.component';
import { PreferencesComponent } from './preferences/preferences.component';
import { OrderHistoryComponent } from './order-history/order-history.component';
import { ResetPasswordComponent } from './login/forget-password/reset-password/reset-password.component';
import { ForgetPasswordComponent } from './login/forget-password/forget-password.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ProductDetailsComponent } from './product-details/product-details.component';
import { CartViewComponent } from './cart/cart-view/cart-view.component';
import { SearchItemComponent } from './search-item/search-item.component';
import { FeaturedItemComponent } from './featured-item/featured-item.component';
import { CategoryListComponent } from './category-hierarchy/category-list.component';
import { HomeRightSideComponent } from './home/home-right-side-component';
import { RecommendedItemComponent } from './recommended-item/recommended-item.component';
import { CategoryHierarchyComponent } from './category-hierarchy/category.component';
import { PageNotFoundComponent } from './not-found.component';
import { Routes, RouterModule, Event, NavigationStart, NavigationEnd, NavigationError, NavigationCancel } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { AboutusComponent } from './static/aboutus/aboutus.component';
import { ContactusComponent } from './static/contactus/contactus.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AddressBookComponent } from "./addressbook/addressbook.component";
import { PreferredStoreComponent } from "./preferred-store/preferred-store.component";
const MAINMENU_ROUTES: Routes = [

  { path: '', redirectTo: '/home', pathMatch: 'full' },
  {
    path: 'home', component: HomeComponent, children: [
      {
        path: '', component: HomeRightSideComponent,
      },
      {
        path: 'category/:id',
        component: CategoryListComponent
        // resolve: {category: CategoryResolver}
      }
    ]
  },
  { path: 'login', component: LoginComponent },
  { path: 'addressbook', component: AddressBookComponent },
  { path: 'forgetPassword', component: ForgetPasswordComponent },
  { path: 'search', component: SearchItemComponent },
  { path: 'product/:name', component: ProductDetailsComponent },
  { path: 'cart', component: CartViewComponent },
  { path: 'wishlist', component: AppWishlistComponent },
  { path: 'checkout', component: CheckoutComponent },
  { path: 'api/user/resetPassword/:token', component: ResetPasswordComponent },
  { path: 'about-us', component: AboutusComponent },
  { path: 'contact-us', component: ContactusComponent },
  { path: 'orderHistory', component: OrderHistoryComponent },
  { path: 'userprofile', component: UserProfileComponent },
  { path: 'preferences', component: PreferencesComponent },
  { path: 'order-history-detail/:orderId', component: OrderHistoryDetailComponent },
  { path: 'preferred-store', component: PreferredStoreComponent },
  { path: '**', component: PageNotFoundComponent }

];
const CATEGORIES_ROUTES: Routes = [
  {
    path: 'category', component: CategoryHierarchyComponent,
    // { path: 'category/:id', component: FeaturedItemComponent,
    children: [
      {
        path: '',
        component: CategoryListComponent

      }
    ]
  }
];
export const CONST_ROUTING = RouterModule.forRoot(MAINMENU_ROUTES);

export const CONST_CATEGORY_ROUTING = RouterModule.forChild(CATEGORIES_ROUTES);
