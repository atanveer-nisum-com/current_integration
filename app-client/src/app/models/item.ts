import { ItemImage } from './itemimage';
import { ItemPrice } from './itemprice';
export interface Item {
    id: number;
    code: string;
    createdAt: number;
    description: string;
    isBuyable: number;
    isDeleted: number;
    isDisplayable: number;
    name: string;
    quantity: number;
    updatedAt: number;
    itemPrice?: ItemPrice[];
    itemImages?: ItemImage[];
}
