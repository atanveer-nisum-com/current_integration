
package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;


/**
 * The Class CountryBeanDTO.
 */
public class UserPaymentCardDTO extends BaseDTO implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6108006009688607332L;
	
	private Long cardNumber;
	
	private Long paymentTypeId;
	
	private byte isDefault;

	public Long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Long getPaymentTypeId() {
		return paymentTypeId;
	}

	public void setPaymentTypeId(Long paymentTypeId) {
		this.paymentTypeId = paymentTypeId;
	}

	public byte getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(byte isDefault) {
		this.isDefault = isDefault;
	}
	
	
	
    
}
