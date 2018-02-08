package com.nisum.shop.service;

import java.util.List;

import com.nisum.common.shop.dto.ProductDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;


/**
 * The Interface OrderItemService.
 */
public interface OrderItemService {

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the order item
	 */
	public OrderItem findOne(Long id);
	
	
	/**
	 * Save order item.
	 *
	 * @param order the order
	 * @param productDTO the product
	 * @return the order item
	 */	
	public OrderItem saveOrderItem(Order order, ProductDTO product);

	/**
	 * Removes the order item.
	 *
	 * @param orderItemId the order item id
	 */
	public void removeOrderItem(Long orderItemId);
	
	/**
	 * Update order item.
	 *
	 * @param orderItemId the order item id
	 * @param quantity the quantity
	 * @return the order item
	 */
	public OrderItem updateOrderItem(Long orderItemId, Integer quantity);
	
	/**
	 * Gets the order items count.
	 *
	 * @param orderId the order id
	 * @return the order items count
	 */
	public Integer getOrderItemsCount(Long orderId);
	
	/**
	 * Delete all order items.
	 *
	 * @param orderId the order id
	 */
	public void deleteAllOrderItems(Long orderId);
	
	/**
	 * Gets the order items by order id.
	 *
	 * @param orderId the order id
	 * @return the order items by order id
	 */
	public List<OrderItem> getOrderItemsByOrderId(Long orderId);


	public OrderItem setPricesAndTaxation(ProductDTO item, OrderItem orderItem);

	/**
	 * Gets the order item by order id and item id.
	 *
	 * @param orderId the order id
	 * @param itemId the item UUID
	 * @return the order item by order id and item UUID
	 */
	public OrderItem getOrderItemByOrderIdAndItemUUId(Long orderId, String itemUUID);
}
