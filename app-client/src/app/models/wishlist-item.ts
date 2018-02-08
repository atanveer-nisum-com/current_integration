import { Item } from './item';
export interface WishlistItem {
  id: number;
  isDeleted: boolean;
  item: Item;
}