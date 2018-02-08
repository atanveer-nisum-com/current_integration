/**
 * 
 */
package com.nisum.shop.dto;

import com.nisum.shop.service.CountryService;
import com.nisum.shop.service.StateService;
import com.nisum.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * Class for Registering the shopper while checkout and/or saving the Guest user
 * information.
 *
 * @author Owais Majid
 */

@Component
public class RegistrationDTO implements Serializable {

	/** The user id. */
	private Long userId;

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

	/** The state service. */
	@Autowired
	private StateService stateService;

	/** The country service. */
	@Autowired
	private CountryService countryService;

	/** The user service. */
	@Autowired
	private UserService userService;

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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

	/**
	 * Sets the user service.
	 *
	 * @param userService
	 *            the new user service
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * Sets the state service.
	 *
	 * @param stateService
	 *            the stateService to set
	 */
	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	/**
	 * Sets the country service.
	 *
	 * @param countryService
	 *            the countryService to set
	 */
	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}

	/**
	 * To POJO USER.
	 *
	 * @return the user
	 */
	/*public User toUser() {
		User dao;

		// check whether its a new user or an existing one
		if (this.userId == null || this.userId == 0) {
			dao = new User();
		} else {
			dao = userService.findOne(this.userId);
		}
		dao.setId(this.userId);
		dao.setEmailAddress(this.emailAddress);
		dao.setFirstName(this.firstName);
		dao.setIsDeleted((byte) 0);
		dao.setLastName(this.lastName);

		if (dao.getIsGuest() == 1 && this.isGuest == 0)
			dao.setPassword(AESEncryptionUtils.encrypt(this.password));

		dao.setIsGuest(this.isGuest);

		for (AddressDTO addressDTO : addresses) {
			if (addressDTO != null && new Long(-1).equals(addressDTO.getId())) {
				Address address = new Address();
				if (addressDTO.getStateBean() != null && addressDTO.getStateBean().getId() != null
						&& !addressDTO.getStateBean().getId().toString().equals("")) {
					State existState = stateService.findStateById(new Long(addressDTO.getStateBean().getId()));
					Country country = existState.getCountry();
					address.setCountryBean(country);
					address.setStateBean(existState);
				} else {
					Country country = countryService.findById(new Long(addressDTO.getCountryBean().getId()));
					address.setCountryBean(country);
				}
				address.setIsDeleted(AppConstant.NOT_DELETED);
				address.setIsDefault((byte) addressDTO.getIsDefault());
				address.setAddressLine1(addressDTO.getAddressLine1());
				address.setAddressLine2(addressDTO.getAddressLine2());
				address.setAddressType(addressDTO.getAddressType());
				address.setCity(addressDTO.getCity());
				address.setPhoneNumber(addressDTO.getPhoneNumber());
				address.setZipcode(addressDTO.getZipcode());
				dao.addAddress(address);
			}
		}

		return dao;
	}*/
}
