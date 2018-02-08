package com.nisum.common.shop.dto;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * The Class CheckoutDTO.
 */
@JsonIgnoreProperties
public class CheckoutDTO extends ShopBaseDTO {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** The orderDTO. */
	private OrderDTO order;
	
	/** The userDTO. */
	private UserDTO user;

	/** The map of unavailable items. */
	private Map<String,ProductDTO> mapOfUnavailableItems;	

	/**
	 * Gets the map of unavailable items.
	 *
	 * @return the map of unavailable items
	 */
	public Map<String, ProductDTO> getMapOfUnavailableItems() {
		return mapOfUnavailableItems;
	}
	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public OrderDTO getOrder() {
		return order;
	}
	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	
	public void setOrder(OrderDTO order) {
		this.order = order;
	}
	
	/**
	 * Gets the User.
	 *
	 * @return the user
	 */
	public UserDTO getUser() {
		return user;
	}
	/**
	 * Sets the user.
	 *
	 * @param user the new order
	 */
	public void setUser(UserDTO user) {
		this.user = user;
	}

	/**
	 * Sets the map of unavailable items.
	 *
	 * @param mapOfUnavailableItems the map of unavailable items
	 */
	public void setMapOfUnavailableItems(Map<String, ProductDTO> mapOfUnavailableItems) {
		this.mapOfUnavailableItems = mapOfUnavailableItems;
	}
	
	
}
