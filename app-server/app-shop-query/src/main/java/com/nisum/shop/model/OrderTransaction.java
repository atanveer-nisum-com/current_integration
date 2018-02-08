package com.nisum.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * The persistent class for the transaction database table.
 * 
 */
@JsonIgnoreProperties({"user","orders","hibernateLazyInitializer", "handler"})
public class OrderTransaction extends BaseModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;

	/** The comments. */
	private String comments;

	/** The currency. */
	private String currency;

	/** The currency code. */
	private String currencyCode;

	/** The payment. */
	private BigDecimal payment;

	/** The transaction status. */
	private Short transactionStatus;
	
	/** The transaction id. */
	private String transactionId;
	
	/** The transaction date and time. */
	private Long transactionTime;

	
	/** The updated at. */
	private Long updatedAt;
	
	/** The orders. */
	//bi-directional many-to-one association to Order
	private Set<Order> orders;

	/** The payment type. */
	//bi-directional many-to-one association to PaymentType

	/** The user. */
	//bi-directional many-to-one association to User
	private User user;
	
	

	/**
	 * Instantiates a new order transaction.
	 */
	public OrderTransaction() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return this.comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}

	/**
	 * Gets the currency.
	 *
	 * @return the currency
	 */
	public String getCurrency() {
		return this.currency;
	}

	/**
	 * Sets the currency.
	 *
	 * @param currency the new currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return this.currencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode the new currency code
	 */
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * Gets the payment.
	 *
	 * @return the payment
	 */
	public BigDecimal getPayment() {
		return this.payment;
	}

	/**
	 * Sets the payment.
	 *
	 * @param payment the new payment
	 */
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	/**
	 * Gets the transaction status.
	 *
	 * @return the transaction status
	 */
	public short getTransactionStatus() {
		return this.transactionStatus;
	}

	/**
	 * Sets the transaction status.
	 *
	 * @param transactionStatus the new transaction status
	 */
	public void setTransactionStatus(short transactionStatus) {
		this.transactionStatus = transactionStatus;
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
	 * @param transactionId the transactionId to set
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
	public Set<Order> getOrders() {
		return this.orders;
	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Adds the order.
	 *
	 * @param order the order
	 * @return the order
	 */
	public Order addOrder(Order order) {
		getOrders().add(order);
		//order.setTransaction(this);
		return order;
	}

	/**
	 * Removes the order.
	 *
	 * @param order the order
	 * @return the order
	 */
	public Order removeOrder(Order order) {
		getOrders().remove(order);
		//order.setTransaction(null);
		return order;
	}

	/**
	 * Gets the payment type.
	 *
	 * @return the payment type
	 */

	/**
	 * Sets the payment type.
	 *
	 * @param paymentType the new payment type
	 */

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	 /*(non-Javadoc)
	 * @see com.nisum.model.BaseModel#hashCode()*/
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	 /*(non-Javadoc)
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)*/
	 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * @return the transactionTime
	 */
	public Long getTransactionTime() {
		return transactionTime;
	}

	/**
	 * @param transactionTime the transactionTime to set
	 */
	public void setTransactionTime(Long transactionTime) {
		this.transactionTime = transactionTime;
	}

	/**
	 * @param transactionStatus the transactionStatus to set
	 */
	public void setTransactionStatus(Short transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	/**
	 * @return the updatedAt
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt the updatedAt to set
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
	

}