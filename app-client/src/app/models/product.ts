import { Item } from './item';
import { Category } from './category';
import { Attribute } from './attribute';

export interface Product {
    id: number;
    createdAt: number;
    description: string;
    isDeleted: number;
    sku: string;
    updatedAt: number;
    attributes: Attribute[];
    category: Category;
    item?: Item;
}
