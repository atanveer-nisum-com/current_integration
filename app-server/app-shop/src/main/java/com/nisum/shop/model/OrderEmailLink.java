package com.nisum.shop.model;

import javax.persistence.*;
import java.io.Serializable;



/**
 * The persistent class for the order_email_link database table.
 * 
 */
@Entity
@Table(name="order_email_link")
@NamedQuery(name="OrderEmailLink.findAll", query="SELECT o FROM OrderEmailLink o")
public class OrderEmailLink extends BaseModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Long id;

	/** The status. */
	@Column(nullable=false)
	private Short status;

	/** The email. */
	//bi-directional many-to-one association to Email
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="email_id", nullable=false)
	private Email email;

	/** The order. */
	//bi-directional many-to-one association to Order
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="order_id", nullable=false)
	private Order order;

	/**
	 * Instantiates a new order email link.
	 */
	public OrderEmailLink() {
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
	 * Gets the status.
	 *
	 * @return the status
	 */
	public Short getStatus() {
		return this.status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(Short status) {
		this.status = status;
	}

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public Email getEmail() {
		return this.email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(Email email) {
		this.email = email;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return this.order;
	}

	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
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