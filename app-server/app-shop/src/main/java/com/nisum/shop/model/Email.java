package com.nisum.shop.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;



/**
 * The persistent class for the email database table.
 * 
 */
@Entity
@Table(name="email")
public class Email extends BaseModel implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(unique=true, nullable=false)
	private Integer id;

	/** The email body. */
	@Lob
	@Column(name="email_body")
	private String emailBody;

	/** The is deleted. */
	@Column(name="is_deleted", nullable=false)
	private Byte isDeleted;
	
	/** The email type. */
	@Column(name="email_type")
	private String emailType;
	
	/** The email subject. */
	@Column(name="email_subject")
	private String emailSubject;

	/** The order email links. */
	//bi-directional many-to-one association to OrderEmailLink
	@OneToMany(mappedBy="email")
	private Set<OrderEmailLink> orderEmailLinks;

	/**
	 * Instantiates a new email.
	 */
	public Email() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Gets the email body.
	 *
	 * @return the email body
	 */
	public String getEmailBody() {
		return this.emailBody;
	}

	/**
	 * Sets the email body.
	 *
	 * @param emailBody the new email body
	 */
	public void setEmailBody(String emailBody) {
		this.emailBody = emailBody;
	}

	/**
	 * Gets the checks if is deleted.
	 *
	 * @return the checks if is deleted
	 */
	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	/**
	 * Sets the checks if is deleted.
	 *
	 * @param isDeleted the new checks if is deleted
	 */
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	/**
	 * Gets the order email links.
	 *
	 * @return the order email links
	 */
	public Set<OrderEmailLink> getOrderEmailLinks() {
		return this.orderEmailLinks;
	}

	/**
	 * Sets the order email links.
	 *
	 * @param orderEmailLinks the new order email links
	 */
	public void setOrderEmailLinks(Set<OrderEmailLink> orderEmailLinks) {
		this.orderEmailLinks = orderEmailLinks;
	}

	/**
	 * Adds the order email link.
	 *
	 * @param orderEmailLink the order email link
	 * @return the order email link
	 */
	public OrderEmailLink addOrderEmailLink(OrderEmailLink orderEmailLink) {
		getOrderEmailLinks().add(orderEmailLink);
		orderEmailLink.setEmail(this);

		return orderEmailLink;
	}

	/**
	 * Removes the order email link.
	 *
	 * @param orderEmailLink the order email link
	 * @return the order email link
	 */
	public OrderEmailLink removeOrderEmailLink(OrderEmailLink orderEmailLink) {
		getOrderEmailLinks().remove(orderEmailLink);
		orderEmailLink.setEmail(null);

		return orderEmailLink;
	}
	
	/**
	 * Gets the email type.
	 *
	 * @return the email type
	 */
	public String getEmailType() {
		return emailType;
	}

	/**
	 * Sets the email type.
	 *
	 * @param emailType the new email type
	 */
	public void setEmailType(String emailType) {
		this.emailType = emailType;
	}

	
	
	/**
	 * Gets the email subject.
	 *
	 * @return the email subject
	 */
	public String getEmailSubject() {
		return emailSubject;
	}

	/**
	 * Sets the email subject.
	 *
	 * @param emailSubject the new email subject
	 */
	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
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
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Email other = (Email) obj;
		if (id != other.id)
			return false;
		return true;
	}

}