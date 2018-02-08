import { AddToCartComponent } from './../cart/add-to-cart/add-to-cart.component';
import { SharedModule } from './../shared/shared.module';
import { CategoryResolver } from './../service/category-resolver.service';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { CategoryListComponent } from './category-list.component';
import { CommonModule } from '@angular/common';
import { CategoryService } from './../service/category-service';
import { RemoveSpaceAndSpecialCharsFilter } from './remove-space.filter';
// Angular Imports
import { NgModule } from '@angular/core';

// This Module's Components
import { CategoryHierarchyComponent } from './category.component';
// Routing Module
import { CategoryRoutingModule } from './category-routing.module';

@NgModule({
    imports: [
        CategoryRoutingModule,
        CommonModule,
        InfiniteScrollModule, SharedModule

    ],
    declarations: [
        CategoryHierarchyComponent,
        RemoveSpaceAndSpecialCharsFilter,
        CategoryListComponent,
		AddToCartComponent
    ],
    exports: [
        CategoryHierarchyComponent,
        AddToCartComponent
    ],
    providers: [CategoryService]
})
export class CategoryModule {

}
