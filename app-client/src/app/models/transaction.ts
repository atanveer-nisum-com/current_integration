import { Payment } from './payment';
export interface Transaction {
    id: number;
    comments: string;
    currency: string;
    currencyCode: string;
    payment: number;
    transactionStatus: number;
    transactionId: string;
    transactionTime: number;
    updatedAt: number;
    paymentType: Payment;
}
