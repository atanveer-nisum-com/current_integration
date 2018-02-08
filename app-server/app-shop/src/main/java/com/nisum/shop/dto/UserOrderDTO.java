package com.nisum.shop.dto;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.util.DateUtils;

import java.io.Serializable;



/**
 * The Class UserOrderDTO.
 */
public class UserOrderDTO extends BaseDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The user id. */
	private Long userId;
	
	/** The start date. */
	private String startDate;
	
	/** The end date. */
	private String endDate;
	
	/** The order id. */
	private Long orderId;

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate() {
		return startDate;
	}

	/**
	 * Sets the start date.
	 *
	 * @param startDate the new start date
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate() {
		return endDate;
	}

	/**
	 * Sets the end date.
	 *
	 * @param endDate the new end date
	 */
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * Gets the start date in long.
	 *
	 * @return the start date in long
	 */
	public Long getStartDateInLong(){
		return startDate == null ? (DateUtils.getCurrentTime() - DateUtils.convertDaysIntoSeconds(AppConstant.USER_ORDERS_FOR_LAST_SEVEN_DAYS)) 
					: DateUtils.convertDateIntoSeconds(startDate);
	}

	/**
	 * Gets the end date in long.
	 *
	 * @return the end date in long
	 */
	public Long getEndDateInLong(){
		return endDate == null ? DateUtils.getCurrentTime() : DateUtils.convertDateEndTimeIntoSeconds(endDate);
	}

} 
