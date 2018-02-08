package com.nisum.common.shop.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import com.nisum.common.util.BaseEntity;


/**
 * The persistent class for the transaction database table.
 *
 */
public class Transaction extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private Long id;

	/** The comments. */
	private String comments;

	/** The currency. */
	private String currency;

	/** The payment. */
	private BigDecimal payment;

	private int paymentTypeId;

	/** The transaction id. */
	private String transactionId;

	/** The transaction status. */
	private Short transactionStatus;

	/** The transaction date and time. */
	private Long transactionTime;

	/** The updated at. */
	private Long updatedAt;

	// bi-directional many-to-one association to Order
	private Set<Order> orders;

	public Transaction() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public BigDecimal getPayment() {
		return payment;
	}

	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}

	public int getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(int paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Short getTransactionStatus() {
		return transactionStatus;
	}

	public void setTransactionStatus(Short transactionStatus) {
		this.transactionStatus = transactionStatus;
	}

	public Long getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Long transactionTime) {
		this.transactionTime = transactionTime;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Set<Order> getOrders() {
		return this.orders;
	}

	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setTransaction(this);

		return order;
	}

	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setTransaction(null);

		return order;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nisum.model.BaseModel#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}
}