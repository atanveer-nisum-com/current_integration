import { OrderHistoryDetailComponent } from './order-history/order-history-list/order-history-detail/order-history-detail.component';
import { AppWishlistComponent } from './wishlist/app-wishlist.component';
import { WishlistComponent } from './wishlist/wishlist.component';
import {PreferencesComponent} from './preferences/preferences.component';
import {UserProfileComponent} from './user-profile/user-profile.component';
import {ProductComponent} from './product-details/product/product.component';
import {OrderHistoryModule} from './order-history/order-history.module';
import {forgotPasswordReducer} from './state-management/reducer/forgot-password.reducer';
import {userReducer} from './state-management/reducer/user.reducer';
import {ResetPasswordModule} from './login/forget-password/reset-password/reset-password.module';
import {ForgetPasswordModule} from './login/forget-password/forget-password.module';
import {guestCheckoutReducer} from './state-management/reducer/guest.reducer';
import {GuestState} from './state-management/state/guest-checkout-state';
import {ShipmentMethodComponent} from './checkout/shipment-method/shipment-method.component';
import {DynamicCheckoutComponent} from './checkout/dynamic-checkout/dynamic-checkout.component';
import {CustomerCheckoutComponent} from './checkout/customer-checkout/customer-checkout.component';
import {DynamicComponent} from './checkout/dynamic-account/dynamic.component';
import {RegisterCheckoutComponent} from './checkout/register-checkout/register-checkout.component';
import {GuestCheckoutComponent} from './checkout/guest-checkout/guest-checkout.component';
import {CheckoutComponent} from './checkout/checkout.component';
import {AddToCartComponent} from './cart/add-to-cart/add-to-cart.component';
import {ProductDetailsComponent} from './product-details/product-details.component';
import {CategoryService} from './service/category-service';
import {SharedModule} from './shared/shared.module';
import {CartViewModule} from './cart/cart-view/cart-view.module';
import {CartService} from './service/cart.service';
import {NguiAutoCompleteModule} from './suggestion/core/auto-complete.module';
import {SuggestionComponent} from './suggestion/suggestion.component';
import {ToolTipDirective} from './shared/tooltip.directive';
import {SearchItemComponent} from './search-item/search-item.component';
import {LoginFormComponent} from './login/login-form/login-form.component';
import {SlideComponent} from './carousel/slide.component';
import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpModule} from '@angular/http';
import {HeaderComponent} from './header/header.component';
import {FooterComponent} from './footer/footer.component';
import {HomeComponent} from './home/home.component';
import {FeaturedItemComponent} from './featured-item/featured-item.component';
import {RecommendedItemComponent} from './recommended-item/recommended-item.component';
import {LoginComponent} from './login/login.component';
import {CONST_ROUTING} from './app.routing';
import {ReactiveFormsModule, FormsModule} from '@angular/forms';
import {RegisterFormComponent} from './login/register-form/register-form.component';
import {AppComponent} from './app.component';
import {InfiniteScrollModule} from 'ngx-infinite-scroll';
import {CarouselComponent} from './carousel/carousel.component';
// import { TooltipModule } from 'ngx-bootstrap';
import {HomeRightSideComponent} from './home/home-right-side-component';
import {CategoryModule} from './category-hierarchy/category.module';
import {PageNotFoundComponent} from './not-found.component';

import {StoreModule} from '@ngrx/store';
import {cartReducer} from './state-management/reducer/cart.reducer';
import {PaypalModule} from './cart/paypal/paypal-checkout';
import {StaticComponent} from './static/static.component';
import {AboutusComponent} from './static/aboutus/aboutus.component';
import {ContactusComponent} from './static/contactus/contactus.component';
import {PostalCodeService} from "./service/postalcode.service";
import {formReducer} from "./state-management/reducer/form.reducer";
import {AddressBookModule} from "./addressbook/addressbook.module";
import { PreferredStoreComponent } from "./preferred-store/preferred-store.component";


@NgModule({

  imports: [StoreModule.provideStore({
    formState: formReducer,
    cartState: cartReducer,
    guestState: guestCheckoutReducer,
    userState: userReducer,
    forgotPasswordState: forgotPasswordReducer,
  }),
    BrowserModule, AddressBookModule, HttpModule, ReactiveFormsModule,
    InfiniteScrollModule, FormsModule, NguiAutoCompleteModule, CategoryModule,
    CartViewModule, SharedModule, ForgetPasswordModule, ResetPasswordModule, OrderHistoryModule,
    CONST_ROUTING,
    PaypalModule,

  ],
  declarations: [
    AppComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    FeaturedItemComponent,

    RecommendedItemComponent,
    LoginComponent,
    RegisterFormComponent,

    CarouselComponent,
    SlideComponent,
    LoginFormComponent,
    SearchItemComponent,
    ToolTipDirective,
    SuggestionComponent,
    PageNotFoundComponent,
    HomeRightSideComponent,
    ProductDetailsComponent,
    ProductComponent,
    WishlistComponent,
    AppWishlistComponent,

    // All checkout components
    DynamicComponent,
    DynamicCheckoutComponent,
    CheckoutComponent,
    GuestCheckoutComponent,
    RegisterCheckoutComponent,
    CustomerCheckoutComponent,
    ShipmentMethodComponent,
    StaticComponent,
    AboutusComponent,
    ContactusComponent,
    UserProfileComponent,
    PreferencesComponent,
    PreferredStoreComponent,
    OrderHistoryDetailComponent
  ],
  providers: [
    CategoryService, CartService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
