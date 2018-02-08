package com.nisum.shop.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * The persistent class for the address database table.
 * 
 */
@Entity
@Table(name = "address")
@JsonIgnoreProperties({ "user", "orders", "hibernateLazyInitializer", "handler" })
public class Address extends BaseModel implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4001201413871996409L;

	/** The id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	private Long id;

	/** The address line 1. */
	@Column(name = "address_line_1", length = 30)
	private String addressLine1;

	/** The address line 2. */
	@Column(name = "address_line_2", length = 30)
	private String addressLine2;

	/** The address type. */
	@Column(name = "address_type")
	private Byte addressType;

	/** The city. */
	@Column(nullable = false, length = 50)
	private String city;

	/** The phone number. */
	@Column(name = "phone_number", length = 20)
	private String phoneNumber;

	/** The state bean. */
	// bi-directional many-to-one association to Country
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state")
	private State stateBean;

	/** The zipcode. */
	@Column(length = 20)
	private String zipcode;

	/** The country bean. */
	// bi-directional many-to-one association to Country
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "country")
	private Country countryBean;

	/** The user. */
	// bi-directional many-to-one association to User
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	/** The orders. */
	// bi-directional many-to-one association to Order
	@OneToMany(mappedBy = "address")
	private Set<Order> orders;

	/** The is default. */
	@Column(name = "is_default", length = 1)
	private Byte isDefault;

	/** The created at. */
	@Column(name = "created_at")
	private Long createdAt;

	/** The updated at. */
	@Column(name = "updated_at")
	private Long updatedAt;

	/** The is deleted. */
	@Column(name = "is_deleted", nullable=false)
	private Byte isDeleted;

	/**
	 * Instantiates a new address.
	 */
	public Address() {
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
	 * @param id
	 *            the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the address line 1.
	 *
	 * @return the address line 1
	 */
	public String getAddressLine1() {
		return this.addressLine1;
	}

	/**
	 * Sets the address line 1.
	 *
	 * @param addressLine1
	 *            the new address line 1
	 */
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/**
	 * Gets the address line 2.
	 *
	 * @return the address line 2
	 */
	public String getAddressLine2() {
		return this.addressLine2;
	}

	/**
	 * Sets the address line 2.
	 *
	 * @param addressLine2
	 *            the new address line 2
	 */
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/**
	 * Gets the address type.
	 *
	 * @return the address type
	 */
	public Byte getAddressType() {
		return this.addressType;
	}

	/**
	 * Sets the address type.
	 *
	 * @param addressType
	 *            the new address type
	 */
	public void setAddressType(Byte addressType) {
		this.addressType = addressType;
	}

	/**
	 * Gets the city.
	 *
	 * @return the city
	 */
	public String getCity() {
		return this.city;
	}

	/**
	 * Sets the city.
	 *
	 * @param city
	 *            the new city
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the phone number.
	 *
	 * @return the phone number
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * Sets the phone number.
	 *
	 * @param phoneNumber
	 *            the new phone number
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Gets the state bean.
	 *
	 * @return the state bean
	 */
	public State getStateBean() {
		return this.stateBean;
	}

	/**
	 * Sets the state bean.
	 *
	 * @param state
	 *            the new state bean
	 */
	public void setStateBean(State state) {
		this.stateBean = state;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public String getZipcode() {
		return this.zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode
	 *            the new zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the country bean.
	 *
	 * @return the country bean
	 */
	public Country getCountryBean() {
		return this.countryBean;
	}

	/**
	 * Sets the country bean.
	 *
	 * @param countryBean
	 *            the new country bean
	 */
	public void setCountryBean(Country countryBean) {
		this.countryBean = countryBean;
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
	 * @param user
	 *            the new user
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @param orders
	 *            the new orders
	 */
	public void setOrders(Set<Order> orders) {
		this.orders = orders;
	}

	/**
	 * Adds the order.
	 *
	 * @param order
	 *            the order
	 * @return the order
	 */
	public Order addOrder(Order order) {
		getOrders().add(order);
		order.setAddress(this);

		return order;
	}

	/**
	 * Removes the order.
	 *
	 * @param order
	 *            the order
	 * @return the order
	 */
	public Order removeOrder(Order order) {
		getOrders().remove(order);
		order.setAddress(null);

		return order;
	}

	/**
	 * Gets the checks if is default.
	 *
	 * @return the checks if is default
	 */
	public Byte getIsDefault() {
		return isDefault;
	}

	/**
	 * Sets the checks if is default.
	 *
	 * @param isDefault
	 *            the new checks if is default
	 */
	public void setIsDefault(Byte isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Long getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt
	 *            the new created at
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Long getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt
	 *            the new updated at
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Gets the checks if is deleted.
	 *
	 * @return the checks if is deleted
	 */
	public Byte getIsDeleted() {
		return isDeleted;
	}

	/**
	 * Sets the checks if is deleted.
	 *
	 * @param isDeleted
	 *            the new checks if is deleted
	 */
	public void setIsDeleted(Byte isDeleted) {
		this.isDeleted = isDeleted;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
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
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Address other = (Address) obj;
		if (id != other.id)
			return false;
		return true;
	}

}