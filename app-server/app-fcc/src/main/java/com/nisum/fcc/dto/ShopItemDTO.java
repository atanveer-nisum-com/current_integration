package com.nisum.fcc.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;

public class ShopItemDTO extends BaseDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5121893824853389412L;

	/** The id. */
	private String id;

	/** The name. */
	private String name;

	/** The is buyable. */
	private Byte isBuyable;
	
	/** The code. */
	private String code;
	
	/** The quantity. */
	private Integer quantity;

	/** The item price. */
	private ItemPriceDTO itemPrice;
	
	/** The default image. */
	private String defaultImage;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Byte getIsBuyable() {
		return isBuyable;
	}

	public void setIsBuyable(Byte isBuyable) {
		this.isBuyable = isBuyable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public ItemPriceDTO getItemPrice() {
		return itemPrice;
	}

	public void setItemPrice(ItemPriceDTO itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getDefaultImage() {
		return defaultImage;
	}

	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}
}
