package com.nisum.user.api.dto;

import java.io.Serializable;

/**
 * The Class ItemPriceDTO.
 */
public class ItemPriceDTO implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 445149063364305970L;
	
	/** The price. */
	private String price;
	
	 public ItemPriceDTO() {
	}
	
	public ItemPriceDTO(String price) {
		super();
		this.price = price;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	
}
