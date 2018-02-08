
package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;



/**
 * The Class SignUpFormDTO.
 */
@Component
public class SignUpFormDTO extends BaseDTO implements Serializable
{

    /** The email address. */
    private String emailAddress;
    
    /** The first name. */
    private String firstName;
    
    /** The is deleted. */
    //private Byte isDeleted;
    
    /** The last name. */
    private String lastName;
    
    /** The password. */
    private String password;
    
    /** The is guest. */
    //private Byte isGuest;
    
    private Long guestId;
    
    /** The addresses. */
    private List<AddressDTO> addresses = null;
    
    /** The Constant serialVersionUID. */
    private final static long serialVersionUID = -7526863653843141958L;

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
        return firstName;
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
//    public Byte getIsDeleted() {
//        return isDeleted;
//    }
//
//    /**
//     * Sets the checks if is deleted.
//     *
//     * @param isDeleted the new checks if is deleted
//     */
//    public void setIsDeleted(Byte isDeleted) {
//        this.isDeleted = isDeleted;
//    }

    /**
     * Gets the last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
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
     * @param addresses the new addresses
     */
    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
    

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

}
