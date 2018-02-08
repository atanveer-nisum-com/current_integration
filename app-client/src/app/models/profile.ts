import { Address } from './address';

export interface Profile {
  
    id?: number;
    createdAt?: number;
    isDeleted?: number;
    isGuest?: number;
    firstName: string;
    lastName: string;
    emailAddress: string;
    //password?: string;
    //cPassword?: string;
    addresses: Address[];
    rememberMe?: boolean;
    currentPassword?:string;
    newPassword?:string;
    confirmPassword?:string;
  }
