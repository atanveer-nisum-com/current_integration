package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;
import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;


/**
 * The persistent class for the transaction database table.
 *
 */
@Entity
@Table(name="transaction")
@JsonIgnoreProperties({"user","orders","hibernateLazyInitializer", "handler"})
public class Transaction extends BaseEntity implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	/** The comments. */
	@Column(length = 100)
	private String comments;

	/** The currency. */
	@Column(nullable = false, length = 50)
	private String currency;

	/** The payment. */
	@Column(nullable = false, precision = 10)
	private BigDecimal payment;

	@Column(name = "payment_type_id")
	private int paymentTypeId;

	/** The transaction id. */
	@Column(name = "transaction_id")
	private String transactionId;

	/** The transaction status. */
	@Column(name = "transaction_status", nullable = false)
	private Short transactionStatus;

	/** The transaction date and time. */
	@Column(name = "transaction_time")
	private Long transactionTime;

	/** The updated at. */
	@Column(name = "updated_at")
	private Long updatedAt;

	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "transaction")
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