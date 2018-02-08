/**
 * 
 */
package com.nisum.common.constant;

/**
 * The Enum ErrorLevel.
 *
 * @author sakhan
 */
public enum ErrorLevel {

	/** The info. */
	INFO("info"),
	
	/** The warning. */
	WARNING("warning"),
	
	/** The error. */
	ERROR("error");
	
	/** The status. */
	private String status;

	/**
	 * Instantiates a new error level.
	 *
	 * @param status the status
	 */
	private ErrorLevel(String status){
		this.status = status;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public String getValue() {
		return status;
	}
}
