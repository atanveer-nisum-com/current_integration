package com.nisum.common.constant;


/**
 * The Enum OrderStatus.
 */
public enum OrderStatus {
	
	/** The awaiting payment. */
	AWAITING_PAYMENT((short) 0, "Awaiting Payment"),
	
	/** The awaiting fulfillment. */
	AWAITING_FULFILLMENT((short) 1, "Awaiting Fulfillment"),
	
	/** The awaiting shipment. */
	AWAITING_SHIPMENT((short) 2, "Awaiting Shipment"),
	
	/** The awaiting pickup. */
	AWAITING_PICKUP((short) 3, "Awaiting Pickup"),
	
	/** The partially shipped. */
	PARTIALLY_SHIPPED((short) 4, "Partially Shipped"),
	
	/** The completed. */
	COMPLETED((short) 5, "Completed"),
	
	/** The shipped. */
	SHIPPED((short) 6, "Shipped"),
	
	/** The cancelled. */
	CANCELLED((short) 7, "Cancelled"),
	
	/** The declined. */
	DECLINED((short) 8, "Declined"),
	
	/** The refunded. */
	REFUNDED((short) 9, "Refunded"),
	
	/** The disputed. */
	DISPUTED((short) 10, "Disputed"),
	
	/** The verification required. */
	VARIFICATION_REQUIRED((short)11, "Verification Required"),
	
	/** The merge. */
	MERGE((short) 12, "Merged");
	
	/** The status. */
	private short status;
	
	/** The title. */
	private String title;
	
	/**
	 * Instantiates a new order status.
	 *
	 * @param status the status
	 * @param title the title
	 */
	private OrderStatus(short status, String title) {
	    this.status = status;
	    this.title = title;
	}
	
	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public short getValue() {
		return status;
	}
	
	/**
	 * Gets the title.
	 *
	 * @return the title
	 */
	public String getTitle(){
		return title;
	}

}