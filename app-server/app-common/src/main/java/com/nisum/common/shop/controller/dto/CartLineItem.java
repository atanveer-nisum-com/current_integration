package com.nisum.common.shop.controller.dto;

import com.nisum.common.shop.dto.ShopBaseDTO;

public class CartLineItem extends ShopBaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String itemId;
	
	
	
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

}
