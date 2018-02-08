import { Category } from './category';
/**
 * Model class contains category and subcategories
 */
export interface CategoryHierarchy {
        parentCategory: Category;
        subCategories: Category[];
}
