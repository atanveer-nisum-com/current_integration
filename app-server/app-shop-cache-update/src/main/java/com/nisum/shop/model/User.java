package com.nisum.shop.model;

import java.io.Serializable;



/**
 * The persistent class for the user database table.
 * 
 */
public class User extends BaseModel implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5519403097886287502L;

	/** The id. */
	private Long id;

	/** The created at. */
	private Long createdAt;

	/** The email address. */
	private String emailAddress;

	private String firstName;

	private Byte isDeleted;

	private String lastName;

	private String password;

	private Long updatedAt;

	private Byte isGuest;
	
	private String resetToken;

	private String authToken;
	
	private Long tokenValidity;
	
	private Long tokenCreatedAt;

//	private Set<Address> addresses;


	/** The user preferences. */
//	private Set<UserPreference> userPreferences;

	/** The transactions. */
	//bi-directional many-to-one association to OrderTransaction
//	@OneToMany(mappedBy="user")
//	private Set<OrderTransaction> transactions;

	/** The wishlists. */
	//bi-directional many-to-one association to Order
//	private Set<Wishlist> wishlists;

//	private Store store;
	
	/**
	 * Instantiates a new user.
	 */
	public User() {
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
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Long getCreatedAt() {
		return this.createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Long createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return this.emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName the new first name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName the new last name
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

//	public Set<Address> getAddresses() {
//		if (this.addresses == null) 
//			this.addresses = new HashSet<>();
//			return this.addresses;
//	}

//	public void setAddresses(Set<Address> addresses) {
//		this.addresses = addresses;
//	}

//	public Address addAddress(Address address) {
//		getAddresses().add(address);
//		address.setUser(this);
//
//		return address;
//	}

//	public Address removeAddress(Address address) {
//		getAddresses().remove(address);
//		address.setUser(null);
//
//		return address;
//	}

//	public Set<Order> getOrders() {
//		return this.orders;
//	}

//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
	
//	public Set<UserPreference> getUserPreferences() {
//		return userPreferences;
//	}

//	public void setUserPreferences(Set<UserPreference> userPreferences) {
//		this.userPreferences = userPreferences;
//	}
	
//	public Order addOrder(Order order) {
//		getOrders().add(order);
////		order.setUser(this);
//		order.setUserId(this.getId());
//
//		return order;
//	}

//	public Order removeOrder(Order order) {
//		getOrders().remove(order);
////		order.setUser(null);
//		order.setUserId(null);
//
//		return order;
//	}

//	public Set<OrderTransaction> getTransactions() {
//		return this.transactions;
//	}

//	public void setTransactions(Set<OrderTransaction> transactions) {
//		this.transactions = transactions;
//	}

//	public OrderTransaction addTransaction(OrderTransaction transaction) {
//		getTransactions().add(transaction);
//		transaction.setUser(this);
//
//		return transaction;
//	}

//	public OrderTransaction removeTransaction(OrderTransaction transaction) {
//		getTransactions().remove(transaction);
//		transaction.setUser(null);
//
//		return transaction;
//	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	public Byte getIsGuest() {
		return isGuest;
	}

	public void setIsGuest(Byte isGuest) {
		this.isGuest = isGuest;
	}
	
//	public void assignUserToAddresses() {
//		this.addresses.forEach(x->x.setUser(this));
//	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Long getTokenValidity() {
		return tokenValidity;
	}

	public void setTokenValidity(Long tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	public Long getTokenCreatedAt() {
		return tokenCreatedAt;
	}

	public void setTokenCreatedAt(Long tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}
	
//	public Store getStore() {
//		return store;
//	}

//	public void setStore(Store store) {
//		this.store = store;
//	}
	

}