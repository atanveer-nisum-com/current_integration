package com.nisum.user.model;

import java.io.Serializable;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.common.util.BaseEntity;

import java.util.List;


/**
 * The persistent class for the payment_type database table.
 * 
 */
@Entity
@Table(name="payment_type")
@NamedQuery(name="PaymentType.findAll", query="SELECT p FROM PaymentType p")
@JsonIgnoreProperties({"userPaymentCards"})
public class PaymentType extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id")
	private Long id;

	private String name;

	//bi-directional many-to-one association to UserPaymentCard
	@OneToMany(mappedBy="paymentType")
	private List<UserPaymentCard> userPaymentCards;

	public PaymentType() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserPaymentCard> getUserPaymentCards() {
		return this.userPaymentCards;
	}

	public void setUserPaymentCards(List<UserPaymentCard> userPaymentCards) {
		this.userPaymentCards = userPaymentCards;
	}

	public UserPaymentCard addUserPaymentCard(UserPaymentCard userPaymentCard) {
		getUserPaymentCards().add(userPaymentCard);
		userPaymentCard.setPaymentType(this);

		return userPaymentCard;
	}

	public UserPaymentCard removeUserPaymentCard(UserPaymentCard userPaymentCard) {
		getUserPaymentCards().remove(userPaymentCard);
		userPaymentCard.setPaymentType(null);

		return userPaymentCard;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((userPaymentCards == null) ? 0 : userPaymentCards.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PaymentType other = (PaymentType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (userPaymentCards == null) {
			if (other.userPaymentCards != null)
				return false;
		} else if (!userPaymentCards.equals(other.userPaymentCards))
			return false;
		return true;
	}

}