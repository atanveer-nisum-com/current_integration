package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;

import java.io.Serializable;

/**
 * WishlistDefaultNameDTO
 */
public class WishlistDefaultNameDTO extends BaseDTO implements Serializable {


	private static final long serialVersionUID = 1283901216127389113L;
    private String name;
    private Boolean isDefault;

	/**
	 * Gets name.
	 *
	 * @return the name of this wishlist
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets name.
	 *
	 * @param name the name of this wishlist
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets is default.
	 *
	 * @return whether this wishlist is default
	 */
	public Boolean getIsDefault() {
		return isDefault;
	}

	/**
	 * Sets is default.
	 *
	 * @param isDefault whether this wishlist is default
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
}