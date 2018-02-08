package com.nisum.fcc.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The Class ItemPriceDTO.
 */
public class ItemPriceDTO extends BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The price. */
	private BigDecimal price;
	
	 public ItemPriceDTO() {
	}
	
	public ItemPriceDTO(BigDecimal price) {
		super();
		this.price = price;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public BigDecimal getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
