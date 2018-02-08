/**
 * 
 */
package com.nisum.common.shop.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

// TODO: Auto-generated Javadoc
/**
 * Class for Registering the shopper while checkout and/or saving the Guest user
 * information.
 *
 * @author Owais Majid
 */

@Component
public class RegistrationDTO extends ShopBaseDTO implements Serializable {

	/** The email address. */
	private String emailAddress;

	/** The first name. */
	private String firstName;

	/** The last name. */
	private String lastName;

	/** The password. */
	private String password;

	/** The is guest. */
	private Byte isGuest;

	/** The addresses. */
	private List<AddressDTO> addresses = null;

	/** The Constant serialVersionUID. */
	private final static long serialVersionUID = -7189811141982892172L;

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
	 * Gets the first name.
	 *
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the lastName to set
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
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the checks if is guest.
	 *
	 * @return the isGuest
	 */
	public Byte getIsGuest() {
		return isGuest;
	}

	/**
	 * Sets the checks if is guest.
	 *
	 * @param isGuest
	 *            the isGuest to set
	 */
	public void setIsGuest(Byte isGuest) {
		this.isGuest = isGuest;
	}

	/**
	 * Gets the addresses.
	 *
	 * @return the addresses
	 */
	public List<AddressDTO> getAddresses() {
		return addresses;
	}

	/**
	 * Sets the addresses.
	 *
	 * @param addresses
	 *            the addresses to set
	 */
	public void setAddresses(List<AddressDTO> addresses) {
		this.addresses = addresses;
	}

	}
