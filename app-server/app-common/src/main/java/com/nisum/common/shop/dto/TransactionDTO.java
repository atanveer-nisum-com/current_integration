/**
 * 
 */
package com.nisum.common.shop.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class TransactionDTO.
 *
 * @author omkhan
 */
public class TransactionDTO extends ShopBaseDTO implements Serializable {

	/** The transaction id. */
	private String transactionId;

	/** The payment type id. */
	private Long paymentTypeId;

	/** The amount. */
	private BigDecimal amount;

	/** The currency code. */
	private String currencyCode;

	/** The transaction status. */
	private String transactionStatus;

	/** The comments. */
	private String comments;

	/** The order id. */
	private Long orderId;

	/** The Constant serialVersionUID. */
	/* The Constant serialVersionUID. */
	private final static long serialVersionUID = -7189812341982892172L;


	/**
	 * Gets the payment type id.
	 *
	 * @return the paymentTypeId
	 */
	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	/**
	 * Sets the payment type id.
	 *
	 * @param paymentTypeId
	 *            the paymentTypeId to set
	 */
	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currencyCode
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode
	 *            the currencyCode to set
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * Gets the transaction status.
	 *
	 * @return the transactionStatus
	 */
	public String getTransactionStatus() {
		return transactionStatus;
	}

	/**
	 * Sets the transaction status.
	 *
	 * @param transactionStatus
	 *            the transactionStatus to set
	 */
	public void setTransactionStatus(String transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments
	 *            the comments to set
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the transaction id.
	 *
	 * @return the transactionId
	 */
	public String getTransactionId() {
		return transactionId;
	}

	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId
	 *            the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the order id.
	 *
	 * @return the orderId
	 */
	public Long getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	
	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static Long getSerialversionuid() {
		return serialVersionUID;
	}

}