package com.nisum.shop.dto;

/**
 * Class for sending success response of login according to API specification document.
 * 
 * @author tdas
 *
 */
public class TokenDTO {

	/** The token. */
	private String token;
	
	/**
	 * Gets the token
	 * @return the token
	 */
	public String getToken() {
		return token;
	}
	
	/**
	 * Sets the token
	 * @param token
	 * 			the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString() {
		return String.format("TokenDTO [token=%s]", token);
	}

}
