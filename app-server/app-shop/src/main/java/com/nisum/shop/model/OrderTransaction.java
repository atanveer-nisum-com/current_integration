package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
public class OrderTransaction extends BaseModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/** The comments. */
	@Column(length=100)
	private String comments;

	/** The currency. */
	@Column(nullable=false, length=50)
	private String currency;

	/** The currency code. */
	@Column(name="currency_code", nullable=false, length=5)
	private String currencyCode;

	/** The payment. */
	@Column(nullable=false, precision=10)
	private BigDecimal payment;

	/** The transaction status. */
	@Column(name="transaction_status", nullable=false)
	private Short transactionStatus;
	
	/** The transaction id. */
	@Column(name="transaction_id")
	private String transactionId;
	
	/** The transaction date and time. */
	@Column(name="transaction_time")
	private Long transactionTime;

	
	/** The updated at. */
	@Column(name="updated_at")
	private Long updatedAt;
	
	/** The orders. */
	//bi-directional many-to-one association to Order
	@OneToMany(mappedBy="transaction")
	private Set<Order> orders;

	/** The payment type. */
	//bi-directional many-to-one association to PaymentType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="payment_type_id", nullable=false)
	private PaymentType paymentType;

	/** The user. */
	//bi-directional many-to-one association to User
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id", nullable=false)
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
		order.setTransaction(this);

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
		order.setTransaction(null);

		return order;
	}

	/**
	 * Gets the payment type.
	 *
	 * @return the payment type
	 */
	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	/**
	 * Sets the payment type.
	 *
	 * @param paymentType the new payment type
	 */
	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

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

	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)
	 */
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