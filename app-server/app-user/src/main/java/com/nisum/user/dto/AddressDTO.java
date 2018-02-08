
package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;


/**
 * The Class AddressDTO.
 */
public class AddressDTO extends BaseDTO implements Serializable
{
	/** The address id. */
	private Long id;

    /** The address line 1. */
    private String addressLine1;
    
    /** The address line 2. */
    private String addressLine2;
    
    /** The address type. */
    private Byte addressType;
    
    /** The city. */
    private String city;
    
    /** The phone number. */
    private String phoneNumber;
    
    /** The zipcode. */
    private String zipcode;
    
    /** The state bean. */
    private StateBeanDTO stateBean;
    
    /** The country bean. */
    private CountryBeanDTO countryBean;
    
//    /**  The user Id. */
//    private Long userId;
    
    private int isDefault;
    
    /** The Constant serialVersionUID. */
    private final static long serialVersionUID = -7189811142982892172L;

    public AddressDTO() {
	}
    
    
    public AddressDTO(Long id, String addressLine1, String addressLine2, Byte addressType, String city,
			String phoneNumber, String zipcode, StateBeanDTO stateBean, CountryBeanDTO countryBean,
			int isDefault) {
		super();
		this.id = id;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressType = addressType;
		this.city = city;
		this.phoneNumber = phoneNumber;
		this.zipcode = zipcode;
		this.stateBean = stateBean;
		this.countryBean = countryBean;
	//	this.userId = userId;
		this.isDefault = isDefault;
	}



	/**
     * Gets the address line 1.
     *
     * @return the address line 1
     */
    public String getAddressLine1() {
        return addressLine1;
    }

    /**
     * Sets the address line 1.
     *
     * @param addressLine1 the new address line 1
     */
    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    /**
     * Gets the address line 2.
     *
     * @return the address line 2
     */
    public String getAddressLine2() {
        return addressLine2;
    }

    /**
     * Sets the address line 2.
     *
     * @param addressLine2 the new address line 2
     */
    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    /**
     * Gets the address type.
     *
     * @return the address type
     */
    public Byte getAddressType() {
        return addressType;
    }

    /**
     * Sets the address type.
     *
     * @param addressType the new address type
     */
    public void setAddressType(Byte addressType) {
        this.addressType = addressType;
    }

    /**
     * Gets the city.
     *
     * @return the city
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the city.
     *
     * @param city the new city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Gets the phone number.
     *
     * @return the phone number
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * Sets the phone number.
     *
     * @param phoneNumber the new phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

	/**
	 * Gets the state bean.
	 *
	 * @return the state bean
	 */
	public StateBeanDTO getStateBean() {
		return stateBean;
	}

	/**
	 * Sets the state bean.
	 *
	 * @param stateBean the new state bean
	 */
	public void setStateBean(StateBeanDTO stateBean) {
		this.stateBean = stateBean;
	}

	/**
	 * Gets the zipcode.
	 *
	 * @return the zipcode
	 */
	public String getZipcode() {
		return zipcode;
	}

	/**
	 * Sets the zipcode.
	 *
	 * @param zipcode the new zipcode
	 */
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	/**
	 * Gets the country bean.
	 *
	 * @return the country bean
	 */
	public CountryBeanDTO getCountryBean() {
		return countryBean;
	}

	/**
	 * Sets the country bean.
	 *
	 * @param countryBean the new country bean
	 */
	public void setCountryBean(CountryBeanDTO countryBean) {
		this.countryBean = countryBean;
	}

	
	 /**
 	 * Gets the id.
 	 *
 	 * @return the id
 	 */
 	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

//	public Long getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Long userId) {
//		this.userId = userId;
//	}

	public int getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}	
}
