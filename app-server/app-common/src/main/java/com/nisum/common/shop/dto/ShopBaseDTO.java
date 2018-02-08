package com.nisum.common.shop.dto;

import java.io.Serializable;

import com.nisum.common.util.BaseDTO;

public class ShopBaseDTO extends BaseDTO implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
    
	private Long userId;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
}