package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;
/**
 * Class for sending success response of login according to API specification document.
 * 
 * @author tdas
 *
 */
public class TokenDTO extends BaseDTO implements Serializable{

	/**
	 * the serialVersionUID
	 */
	
	private static final long serialVersionUID = -4588258601862182744L;

	/** The token. */
	private String token;
	
	/** User's first name	*/
	private String firstName;
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
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
