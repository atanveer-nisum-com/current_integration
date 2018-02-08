import { Address } from './address';

export interface User {

  userId?: number;
  createdAt?: number;
  isDeleted?: number;
  isGuest?: number;
  firstName: string;
  lastName: string;
  emailAddress: string;
  password: string;
  cPassword?: string;

  addresses: Address[];
  rememberMe?: boolean;
}

