package com.nisum.user.repository;

import com.nisum.user.model.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;



/**
 * The Interface EmailRepository.
 */
public interface EmailRepositoryUser extends JpaRepository<Email, Long>, JpaSpecificationExecutor<Email> {

	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Email> findAll();
	
	/**
	 * Gets the signup email.
	 *
	 * @return the signup email
	 */
	@Query("SELECT e FROM Email e  WHERE emailType='SIGNUP'")
	public Email getSignupEmail();
	
	/**
	 * Gets the order confirmation email.
	 *
	 * @return the order confirmation email
	 */
	@Query("SELECT e FROM Email e  WHERE emailType='ORDER COFIRMATION EMAIL'")
	public Email getOrderConfirmationEmail();
	
	/**
	 * Gets the forget password email.
	 *
	 * @return the forget password email
	 */
	@Query("SELECT e FROM Email e  WHERE emailType='FORGETPASSWORD'")
	public Email getForgetPasswordEmail();

	/**
	 * Gets the reset password email.
	 *
	 * @return the reset password email
	 */
	@Query("SELECT e FROM Email e  WHERE emailType='RESETPASSWORD'")
	public Email getResetPasswordEmail();
	
	/**
	 * Gets the reset password email.
	 *
	 * @return the reset password email
	 */
	@Query("SELECT e FROM Email e  WHERE emailType='UPDATEPROFILE'")
	public Email getProfileUpdateEmail();
	
	/**
	 * Gets the cancel order email.
	 *
	 * @return the cancel order email
	 */
	@Query("SELECT e FROM Email e WHERE emailType='CANCEL ORDER'")
	public Email getCancelOrderEmail();


}

