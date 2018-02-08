package com.nisum.fcc.dto;

import com.nisum.common.util.BaseDTO;

public class ShopProductDTO extends BaseDTO {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3075464814286651973L;

	private String id;

	/** The sku. */
	private String sku;

	private String categoryName;

	/** The item. */
	private ShopItemDTO item;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ShopItemDTO getItem() {
		return item;
	}

	public void setItem(ShopItemDTO item) {
		this.item = item;
	}
}
