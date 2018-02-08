
package com.nisum.shop.dto;

import com.nisum.shop.model.Address;
import com.nisum.shop.model.Country;
import com.nisum.shop.model.State;
import com.nisum.shop.model.User;
import com.nisum.shop.service.CountryService;
import com.nisum.shop.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;



/**
 * The Class SignUpFormDTO.
 */
@Component
public class SignUpFormDTO implements Serializable
{

    /** The email address. */
    private String emailAddress;
    
    /** The first name. */
    private String firstName;
    
    /** The is deleted. */
    private Byte isDeleted;
    
    /** The last name. */
    private String lastName;
    
    /** The password. */
    private String password;
    
    /** The is guest. */
    private Byte isGuest;
    
    private Long guestId;
    
    /** The addresses. */
    private List<AddressDTO> addresses = null;

	/** The state service. */
	@Autowired StateService stateService;
	
	/** The country service. */
	@Autowired CountryService countryService;
	
    
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
    public Byte getIsDeleted() {
        return isDeleted;
    }

    /**
     * Sets the checks if is deleted.
     *
     * @param isDeleted the new checks if is deleted
     */
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }

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
     * Gets the checks if is guest.
     *
     * @return the checks if is guest
     */
    public Byte getIsGuest() {
        return isGuest;
    }

    /**
     * Sets the checks if is guest.
     *
     * @param isGuest the new checks if is guest
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
     * @param addresses the new addresses
     */
    public void setAddresses(List<AddressDTO> addresses) {
        this.addresses = addresses;
    }
    
    /**
     * To user.
     *
     * @return the user
     */
    public User toUser() {
    	User dao = new User();
    	dao.setEmailAddress(this.emailAddress);
    	dao.setFirstName(this.firstName);
    	dao.setIsDeleted(this.isDeleted);
    	dao.setLastName(this.lastName);
    	dao.setPassword(this.password);
    	dao.setIsGuest(this.isGuest);
    	AddressDTO addressDTO = addresses.get(0);
    	if (addressDTO !=  null) {
    		Address address = new Address();
    		if (addressDTO.getStateBean() != null) {
    			State existState = stateService.findStateById(new Long(addressDTO.getStateBean().getId()));
    			Country country = existState.getCountry();
    			address.setCountryBean(country);
    			address.setStateBean(existState);
    		}else {
    			Country country = countryService.findById(new Long(addressDTO.getCountryBean().getId()));
    			address.setCountryBean(country);
    		}
    		address.setAddressLine1(addressDTO.getAddressLine1());
    		address.setAddressLine2(addressDTO.getAddressLine2());
    		address.setAddressType(addressDTO.getAddressType());
    		address.setCity(addressDTO.getCity());
    		address.setPhoneNumber(addressDTO.getPhoneNumber());
    		address.setZipcode(addressDTO.getZipcode());
    		dao.addAddress(address);
    	}
    	
    	return dao;
    }

	/**
	 * Sets the state service.
	 *
	 * @param stateService the state service
	 * @return the sign up form DTO
	 */
	public SignUpFormDTO setStateService(StateService stateService) {
		this.stateService  =stateService ;
		return this;
	}

	/**
	 * Sets the country service.
	 *
	 * @param countryService the country service
	 * @return the sign up form DTO
	 */
	public SignUpFormDTO setCountryService(CountryService countryService) {
		this.countryService = countryService;
		return this;
	}

	/**
	 * Sets the service.
	 *
	 * @param stateService the state service
	 * @param countryService the country service
	 * @return the sign up form DTO
	 */
	public SignUpFormDTO setService(StateService stateService, CountryService countryService) {
		this.countryService = countryService;
		this.stateService  =stateService ;
		return this;
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
