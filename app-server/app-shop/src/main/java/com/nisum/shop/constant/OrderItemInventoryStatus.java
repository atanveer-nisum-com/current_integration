/**
 * 
 */
package com.nisum.shop.constant;


/**
 * The Enum OrderItemInventoryStatus.
 *
 * @author Owais Majid
 */
public enum OrderItemInventoryStatus {

	/** The out of stock. */
	OUT_OF_STOCK((short) 0),
	
	/** The available. */
	AVAILABLE((short) 1);
	
	/**
	 * Instantiates a new order item inventory status.
	 *
	 * @param status the status
	 */
	OrderItemInventoryStatus(short status){
		this.status=status;
	}
	
	/** The status. */
	private short status;

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public short getValue() {
		return status;
	}

		
	
	
	
	
}
