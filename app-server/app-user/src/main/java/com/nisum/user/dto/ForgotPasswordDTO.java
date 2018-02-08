package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;


/**
 * The Class ForgotPasswordDTO.
 */
public class ForgotPasswordDTO extends BaseDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The email. */
	private String email;

	/**
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
} 
