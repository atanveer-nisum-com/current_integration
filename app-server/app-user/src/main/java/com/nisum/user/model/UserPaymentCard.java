package com.nisum.user.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;
import org.hibernate.annotations.Where;

import java.math.BigInteger;
import java.util.Objects;


/**
 * The persistent class for the user_payment_cards database table.
 * 
 */
@Entity
@Table(name="user_payment_cards")
@Where(clause = "is_deleted = 0")
@JsonIgnoreProperties({"user"})
@NamedQuery(name="UserPaymentCard.findAll", query="SELECT u FROM UserPaymentCard u")
public class UserPaymentCard extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name="card_number")
	private Long cardNumber;

	@Column(name="created_at")
	private Long createdAt;

	@Column(name="is_default")
	private Byte isDefault;

	@Column(name="is_deleted")
	private Byte isDeleted;

	@Column(name="updated_at")
	private Long updatedAt;

	//bi-directional many-to-one association to PaymentType
	@ManyToOne
	@JoinColumn(name="payment_type_id")
	private PaymentType paymentType;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	public UserPaymentCard() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCardNumber() {
		return this.cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getCreatedAt() {
		return this.createdAt;
	}

	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	public Byte getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	public Byte getIsDeleted() {
		return this.isDeleted;
	}

	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	public PaymentType getPaymentType() {
		return this.paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPaymentCard other = (UserPaymentCard) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}
}