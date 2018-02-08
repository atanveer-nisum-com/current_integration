package com.nisum.fcc.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;

/**
 * The Class ProductDTO.
 */
public class ProductDTO extends BaseDTO implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	private String id;

	/** The sku. */
	private String sku;

	private String categoryName;

	/** The item. */
	private ItemDTO item;

	/**
	 * Gets the item.
	 *
	 * @return the item
	 */
	public ItemDTO getItem() {
		return item;
	}

	/**
	 * Sets the item.
	 *
	 * @param item
	 *            the new item
	 */
	public void setItem(ItemDTO item) {
		this.item = item;
	}

	/**
	 * Gets the sku.
	 *
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * Sets the sku.
	 *
	 * @param sku
	 *            the new sku
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
