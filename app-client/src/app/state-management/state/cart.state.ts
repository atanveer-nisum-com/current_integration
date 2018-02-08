/**
 * State Of The Cart is mapped on this CartState interface
 * @author Abdul Ghaffar Mallah
 */
export interface CartState {
    /**
     * Reflects current selected item's id,
     * -1 indicates that no item is selected
     */
    selectedItemId: number;
    itemCount: number;
}