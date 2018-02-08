import { Item } from './item';
    export interface OrderItem {
        id: number;
        createdAt: number;
        inventoryStatus: number;
        price?: number;
        quantity: number;
        taxation: number;
        subTotalPrice: number;
        totalPrice:number;
        updatedAt?: number;
        item: Item;
    }
