package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;


/**
 * The Class ResetPasswordDTO.
 */
public class ResetPasswordDTO extends BaseDTO implements Serializable{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The reset token. */
	private String resetToken;
	
	/** The password. */
	private String password;
	
	/** The confirm password. */
	private String confirmPassword;

	/**
	 * Gets the reset token.
	 *
	 * @return the reset token
	 */
	public String getResetToken() {
		return resetToken;
	}

	/**
	 * Sets the reset token.
	 *
	 * @param resetToken the new reset token
	 */
	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
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
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the confirm password.
	 *
	 * @return the confirm password
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * Sets the confirm password.
	 *
	 * @param confirmPassword the new confirm password
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
} 
