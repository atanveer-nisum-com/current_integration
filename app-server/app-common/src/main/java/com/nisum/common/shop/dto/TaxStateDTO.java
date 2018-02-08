package com.nisum.common.shop.dto;

import java.io.Serializable;

/**
 * The Class ItemAddToCartDTO.
 *
 * @author Shaharyar Iqbal
 */
public class TaxStateDTO extends ShopBaseDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The abbrv. */
	private String abbrv;

	/**
	 * Gets the abbrv.
	 *
	 * @return the abbrv
	 */
	public String getAbbrv() {
		return abbrv;
	}

	/**
	 * Sets the abbrv.
	 *
	 * @param abbrv the new abbrv
	 */
	public void setAbbrv(String abbrv) {
		this.abbrv = abbrv;
	}

	

}
