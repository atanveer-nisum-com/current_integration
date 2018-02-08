import { WishlistItem } from './wishlist-item';

export interface Wishlist {
  id: number;
  createdAt: number;
  isDefault: boolean;
  isDeleted: boolean;
  name: string;
  updatedAt?: any;
  wishlistItems: WishlistItem[];
}

