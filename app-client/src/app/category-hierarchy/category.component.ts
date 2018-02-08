import { CategoryHierarchy } from './../models/index';
import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../service/category-service';


@Component({
    selector: 'category-list',
    templateUrl: 'category.component.html',
    styleUrls: ['category.component.css'],
    providers: [CategoryService]
})
/**
 * CategoryHierarchyComponent provides category hierarchy from parent to child
 */
export class CategoryHierarchyComponent implements OnInit {

    categories: CategoryHierarchy[];

    categoryService: CategoryService;

    constructor(categoryService: CategoryService) {
        this.categoryService = categoryService;
    }
    /**
     * Call categoryService api and save response in categories
     */
    ngOnInit(): void {
        this.categoryService.getCategoryHierarchy().subscribe((category: CategoryHierarchy[]) => {
            this.categories = category;
        });
    }
}
