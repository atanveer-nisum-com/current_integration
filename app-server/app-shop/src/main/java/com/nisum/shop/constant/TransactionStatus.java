/**
 * 
 */
package com.nisum.shop.constant;

/**
 * The Enum TransactionStatus.
 *
 * @author omkhan
 */
public enum TransactionStatus {

	/** The declined. */
	DECLINED((short) 0),
	
	/** The success. */
	SUCCESS((short) 1);
	
	/**
	 * Instantiates a new transaction status.
	 *
	 * @param status the status
	 */
	TransactionStatus(short status){
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
