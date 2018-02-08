package com.nisum.user.dto;

import com.nisum.common.util.BaseDTO;
import com.nisum.user.api.dto.ProductDTO;

import java.io.Serializable;
import java.util.List;

public class WishlistResponseDTO extends BaseDTO implements Serializable {

	/**
	 * for Serializable
	 */
	private static final long serialVersionUID = 1283098129839121231L;
	private Long id;
	private String name;
	private Boolean isDefault = Boolean.FALSE;
	private List<ProductDTO> items;
	
	public WishlistResponseDTO() {}
	
	public WishlistResponseDTO(Long id, String name, Boolean isDefault, List<ProductDTO> items) {
		super();
		this.id = id;
		this.name = name;
		this.isDefault = isDefault;
		this.items = items;
	}

	/**
	 * @return id the wishlist id
	 */
	public Long getId() {
		return id;
	}
	
	/**
	 * @param id sets id of wishlist
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return name the name of wishlist
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name of wishlist
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return isDefault whether this is default wishlist
	 */
	public Boolean getIsDefault() {
		return isDefault;
	}
	
	/**
	 * @param isDefault sets the default wishlist flag
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	
	/**
	 * @return List<ProductDTO> the list of items in wishlist
	 */
	public List<ProductDTO> getItems() {
		return items;
	}
	
	/**
	 * @param items sets the list of items of wishlist
	 */
	public void setItems(List<ProductDTO> items) {
		this.items = items;
	}
}
