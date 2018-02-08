/**
 * 
 */
package com.nisum.common.shop.dto;

// TODO: Auto-generated Javadoc
/**
 * The Class ItemAddToCartDTO.
 *
 * @author Nisum017
 */
public class ItemAddToCartDTO extends ShopBaseDTO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The quantity. */
	private Integer quantity;
	/** orderitemid */
	private Long orderItemId;

	public Long getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(Long orderItemId) {
		this.orderItemId = orderItemId;
	}

	/**
	 * Gets the quantity.
	 *
	 * @return the quantity
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Sets the quantity.
	 *
	 * @param quantity the new quantity
	 */
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
