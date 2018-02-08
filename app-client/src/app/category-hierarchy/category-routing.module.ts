import { CategoryService } from './../service/category-service';
import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { CONST_CATEGORY_ROUTING } from './../app.routing';
// import { RemoveSpaceAndSpecialCharsFilter } from './remove-space.filter';


@NgModule({
  imports: [
    CONST_CATEGORY_ROUTING,
  ],
  exports: [
    RouterModule
  ]
})
export class CategoryRoutingModule { }
