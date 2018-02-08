package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



/**
 * The persistent class for the payment_type database table.
 * 
 */
@Entity
@Table(name="payment_type")
@JsonIgnoreProperties({"transactions","hibernateLazyInitializer", "handler"})
public class PaymentType extends BaseModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/** The name. */
	@Column(nullable=false, length=50)
	private String name;

	/** The transactions. */
	//bi-directional many-to-one association to OrderTransaction
	@OneToMany(mappedBy="paymentType")
	private Set<OrderTransaction> transactions;

	/**
	 * Instantiates a new payment type.
	 */
	public PaymentType() {
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
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the transactions.
	 *
	 * @return the transactions
	 */
	public Set<OrderTransaction> getTransactions() {
		return this.transactions;
	}

	/**
	 * Sets the transactions.
	 *
	 * @param transactions the new transactions
	 */
	public void setTransactions(Set<OrderTransaction> transactions) {
		this.transactions = transactions;
	}

	/**
	 * Adds the transaction.
	 *
	 * @param transaction the transaction
	 * @return the order transaction
	 */
	public OrderTransaction addTransaction(OrderTransaction transaction) {
		getTransactions().add(transaction);
		transaction.setPaymentType(this);

		return transaction;
	}

	/**
	 * Removes the transaction.
	 *
	 * @param transaction the transaction
	 * @return the order transaction
	 */
	public OrderTransaction removeTransaction(OrderTransaction transaction) {
		getTransactions().remove(transaction);
		transaction.setPaymentType(null);

		return transaction;
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

}