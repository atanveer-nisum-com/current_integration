import { Transaction } from './transaction';
import { User } from './user';
import { Address } from './address';
import { OrderItem } from './order-item';
    export interface Order {
        id: number;
        createdAt: number;
        orderStatus: number;
        totalItem: number;
        subTotalPrice: number;
        taxation:number;
        orderTotalPrice:number;
        updatedAt: number;
        address?: Address;
        transaction?: Transaction;
        user?: User;
        orderEmailLinks: any[];
        orderItems?: OrderItem[];
        orderStatusTitle: string;
    }
