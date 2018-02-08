package com.nisum.shop.api.dto;

import com.nisum.shop.dto.BaseDTO;

import java.io.Serializable;
import java.math.BigDecimal;
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

	/** The item image. */
	private String defaultImage;
	
	/** The item price. */
	private BigDecimal price = new BigDecimal("100");
	
	/** The item quantity. */
	private Integer quantity = new Integer("10");

	/** The item images. */
	private Set<ItemImageDTO> itemImages;
	
	/** The default image. */

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

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}


	
}
