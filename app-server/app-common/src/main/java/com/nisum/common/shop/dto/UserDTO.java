package com.nisum.common.shop.dto;

import java.io.Serializable;

/**
 * The Class UserDTO.
 */
public class UserDTO extends ShopBaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The token. */
	private String token;

	/** The email address. */
	private String emailAddress;

	/** The password. */
	private String password;

	/** The remember me. */
	private Boolean rememberMe;

	
	private Long guestId;

	/**
	 * @return the guestId
	 */
	public Long getGuestId() {
		return guestId;
	}

	/**
	 * @param guestId the guestId to set
	 */
	public void setGuestId(Long guestId) {
		this.guestId = guestId;
	}

	/** The cart count. */
	private Integer cartCount;

	/**
	 * Gets the cart count.
	 *
	 * @return the cartCount
	 */
	public Integer getCartCount() {
		return cartCount;
	}

	/**
	 * Sets the cart count.
	 *
	 * @param cartCount
	 *            the cartCount to set
	 */
	public void setCartCount(Integer cartCount) {
		this.cartCount = cartCount;
	}
	
	/**
	 * Gets the token.
	 *
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Sets the token.
	 *
	 * @param token
	 *            the new token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Gets the email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * Sets the email address.
	 *
	 * @param emailAddress
	 *            the new email address
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the remember me.
	 *
	 * @return the remember me
	 */
	public Boolean getRememberMe() {
		return rememberMe;
	}

	/**
	 * Sets the remember me.
	 *
	 * @param rememberMe
	 *            the new remember me
	 */
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

}
