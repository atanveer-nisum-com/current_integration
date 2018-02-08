package com.nisum.user.service;

import com.nisum.user.model.User;


/**
 * The Interface EmailService.
 */
public interface EmailService {

	/**
	 * Send simple message.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param text the text
	 */
	public void sendSimpleMessage(String to, String subject, String text);
	
	/**
	 * Send signup email.
	 *
	 * @param user the user
	 */
	public void sendSignupEmail(User user);
	
	/**
	 * Send mime message.
	 *
	 * @param to the to
	 * @param subject the subject
	 * @param text the text
	 */
	public void sendMimeMessage(String to, String subject, String text);
	
	/**
	 * Send forget password mail.
	 *
	 * @param user the user
	 * @param resetToken the reset token
	 */
	public void sendForgetPasswordMail(User user, String resetToken);
	
	/**
	 * Send reset password mail.
	 *
	 * @param user the user
	 */
	public void sendResetPasswordMail(User user);

	/**
	 * Send profile update mail.
	 *
	 * @param user the user
	 */
	public void sendProfileUpdateMail(User user);
	

}
