package com.nisum.fcc.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * The Class ItemDTO.
 */
public class ItemDTO extends BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

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
	private Set<ItemPriceDTO> itemPrice;

	/** The item images. */
	private Set<ItemImageDTO> itemImages;
	
	/** The default image. */
	private String defaultImage;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id
	 *            the new id
	 */
	public void setId(Object id) {
		this.id = id.toString();
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the checks if is buyable.
	 *
	 * @return the checks if is buyable
	 */
	public Byte getIsBuyable() {
		return isBuyable;
	}

	/**
	 * Sets the checks if is buyable.
	 *
	 * @param isBuyable
	 *            the new checks if is buyable
	 */
	public void setIsBuyable(Byte isBuyable) {
		this.isBuyable = isBuyable;
	}

	/**
	 * Gets the item images.
	 *
	 * @return the item images
	 */
	public Set<ItemImageDTO> getItemImages() {
		return itemImages;
	}

	/**
	 * Gets the item price.
	 *
	 * @return the item price
	 */
	public Set<ItemPriceDTO> getItemPrice() {
		itemPrice = itemPrice == null ? new HashSet<>() : itemPrice;
		return itemPrice;
	}

	/**
	 * Sets the item price.
	 *
	 * @param itemPrice
	 *            the new item price
	 */
	public void setItemPrice(Set<ItemPriceDTO> itemPrice) {
		this.itemPrice = itemPrice;
	}

	/**
	 * Sets the item price.
	 *
	 * @param itemPrice the new item price
	 */
	public void addItemPrice(ItemPriceDTO itemPrice) {
		getItemPrice().add(itemPrice);
	}

	/**
	 * Sets the item images.
	 *
	 * @param itemImages
	 *            the new item images
	 */
	public void setItemImages(Set<ItemImageDTO> itemImages) {
		this.itemImages = itemImages;
	}

	/**
	 * Gets the default image.
	 *
	 * @return the default image
	 */
	public String getDefaultImage() {
		return defaultImage;
	}

	/**
	 * Sets the default image.
	 *
	 * @param defaultImage the new default image
	 */
	public void setDefaultImage(String defaultImage) {
		this.defaultImage = defaultImage;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
