package com.nisum.shop.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.User;

import java.util.Map;

/**
 * The Class CheckoutDTO.
 */
@JsonIgnoreProperties
public class CheckoutDTO {
	
	/** The order. */
	private Order order;
	
	/** The user. */
	private User user;

	/** The map of unavailable items. */
	private Map<String,ProductDTO> mapOfUnavailableItems;
	
	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}
	
	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}
	
	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}
	
	/**
	 * Gets the map of unavailable items.
	 *
	 * @return the map of unavailable items
	 */
	public Map<String, ProductDTO> getMapOfUnavailableItems() {
		return mapOfUnavailableItems;
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
