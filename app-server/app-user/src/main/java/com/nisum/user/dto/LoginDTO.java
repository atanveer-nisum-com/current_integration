package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;

/**
 * class for login request information according to API specification document
 * @author tdas
 *
 */
public class LoginDTO extends BaseDTO implements Serializable{
	/**
	 * the serialVersionUID
	 */
	private static final long serialVersionUID = 3645210578177190155L;

	/** The token. */
	private String guestToken;

	/** The email address. */
	private String emailAddress;

	/** The password. */
	private String password;

	/** The remember me. */
	private Boolean rememberMe;
	/**
	 * gets the guest token 
	 * 
	 * @return the guestToken
	 */
	public String getGeustToken() {
		return guestToken;
	}
	/**
	 * Sets guest token
	 * @param guestToken
	 * 			the guestToken to set
	 */			

	public void setGeustToken(String guestToken) {
		this.guestToken = guestToken;
	}
	
	/**
	 * Gets the email address.
	 *
	 * @return the emailAddress
	 */
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	/**
	 * Sets the email address.
	 *
	 * @param emailAddress
	 *            the emailAddress to set
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
	 * Sets the password
	 * @param password
	 * 			the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Gets the remember me.
	 *
	 * @return the rememberMe
	 */
	public Boolean getRememberMe() {
		return rememberMe;
	}
	
	/**
	 * Sets the remember Me
	 * @param rememberMe
	 * 			the remember me to set
	 */
	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}
	
	@Override
	public String toString() {
		return String.format("LoginDTO [guestToken=%s, emailAddress=%s, password=%s, rememberMe=%s]", guestToken,
				emailAddress, password, rememberMe);
	}
	


}
