package com.nisum.shop.model;

import java.io.Serializable;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;



/**
 * The persistent class for the user database table.
 * 
 */
@JsonIgnoreProperties({"orders","transactions","userPreferences","hibernateLazyInitializer", "handler"})
public class User extends BaseModel implements Serializable {
	

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5519403097886287502L;

	/** The id. */
	private Long id;

	/** The created at. */
	private Long createdAt;

	/** The email address. */
	private String emailAddress;

	/** The first name. */
	private String firstName;

	/** The is deleted. */
	private Byte isDeleted;

	/** The last name. */
	private String lastName;

	/** The password. */
	@JsonIgnore
	private String password;

	/** The updated at. */
	private Long updatedAt;

	/** The is guest. */
	private Byte isGuest;
	
	/** The reset token. */
	private String resetToken;

	/** The auth token. */
	private String authToken;
	
	/** The token validity. */
	private Long tokenValidity;
	
	/** The token created at. */
	private Long tokenCreatedAt;

	/** The addresses. */
	//bi-directional many-to-one association to Address


	/** The user preferences. */
	//bi-directional many-to-one association to User Preference
	private Set<UserPreference> userPreferences;

	/** The transactions. */
	//bi-directional many-to-one association to OrderTransaction
	private Set<OrderTransaction> transactions;

	/** The wishlists. */
	//bi-directional many-to-one association to Order
//	private Set<Wishlist> wishlists;

	/** The store. */
	
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

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Long getUpdatedAt() {
		return this.updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */

	/**
	 * Sets the addresses.
	 *
	 * @param addresses the new addresses
	 */

	/**
	 * Adds the address.
	 *
	 * @param address the address
	 * @return the address
	 */

	/**
	 * Removes the address.
	 *
	 * @param address the address
	 * @return the address
	 */

	/**
	 * Gets the orders.
	 *
	 * @return the orders
	 */
//	public Set<Order> getOrders() {
//		return this.orders;
//	}

	/**
	 * Sets the orders.
	 *
	 * @param orders the new orders
	 */
//	public void setOrders(Set<Order> orders) {
//		this.orders = orders;
//	}
	
	/**
	 * Gets the user preferences.
	 *
	 * @return the user preferences
	 */
	public Set<UserPreference> getUserPreferences() {
		return userPreferences;
	}

	/**
	 * Sets the user preferences.
	 *
	 * @param userPreferences the new user preferences
	 */
	public void setUserPreferences(Set<UserPreference> userPreferences) {
		this.userPreferences = userPreferences;
	}
	
	/**
	 * Adds the order.
	 *
	 * @param order the order
	 * @return the order
	 */
//	public Order addOrder(Order order) {
//		getOrders().add(order);
////		order.setUser(this);
//		order.setUserId(this.getId());
//
//		return order;
//	}

	/**
	 * Removes the order.
	 *
	 * @param order the order
	 * @return the order
	 */
//	public Order removeOrder(Order order) {
//		getOrders().remove(order);
////		order.setUser(null);
//		order.setUserId(null);
//
//		return order;
//	}

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
		transaction.setUser(this);

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
		transaction.setUser(null);

		return transaction;
	}

	 /**(non-Javadoc)
	 * @see com.nisum.model.BaseModel#hashCode()*/
	 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	 /**(non-Javadoc)
	 * @see com.nisum.model.BaseModel#equals(java.lang.Object)*/
	 
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/**
	 * Gets the checks if is guest.
	 *
	 * @return the checks if is guest
	 */
	public Byte getIsGuest() {
		return isGuest;
	}

	/**
	 * Sets the checks if is guest.
	 *
	 * @param isGuest the new checks if is guest
	 */
	public void setIsGuest(Byte isGuest) {
		this.isGuest = isGuest;
	}
	
	/**
	 * Assign user to addresses.
	 */

	/**
	 * Gets the reset token.
	 *
	 * @return the resetToken
	 */
	public String getResetToken() {
		return resetToken;
	}

	/**
	 * Sets the reset token.
	 *
	 *  @param resetToken the new resetToken
	 */
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	/**
	 * Gets the auth token.
	 *
	 * @return the auth token
	 */
	public String getAuthToken() {
		return authToken;
	}

	/**
	 * Sets the auth token.
	 *
	 * @param authToken the new auth token
	 */
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	/**
	 * Gets the token validity.
	 *
	 * @return the token validity
	 */
	public Long getTokenValidity() {
		return tokenValidity;
	}

	/**
	 * Sets the token validity.
	 *
	 * @param tokenValidity the new token validity
	 */
	public void setTokenValidity(Long tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	/**
	 * Gets the token created at.
	 *
	 * @return the token created at
	 */
	public Long getTokenCreatedAt() {
		return tokenCreatedAt;
	}

	/**
	 * Sets the token created at.
	 *
	 * @param tokenCreatedAt the new token created at
	 */
	public void setTokenCreatedAt(Long tokenCreatedAt) {
		this.tokenCreatedAt = tokenCreatedAt;
	}
	
	

}