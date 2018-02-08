package com.nisum.shop.service;

import com.nisum.shop.api.dto.ProductDTO;
import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.User;

import java.util.List;

/**
 * The Interface OrderService.
 */
public interface OrderService {

	/**
	 * Find one.
	 *
	 * @param id
	 *            the id
	 * @return the order
	 */
	public Order findOrderById(Long id);

	/**
	 * Gets the active order by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the active order by user id
	 */
	public Order getActiveOrderByUserId(Long userId);
	

	/**
	 * Gets the all active order.
	 *
	 * @return the all active order
	 */
	public List<Order> getAllActiveOrder();

	/**
	 * Save.
	 *
	 * @param userId
	 *            the user id
	 * @param itemId
	 *            the item id
	 * @return the cartDto
	 */
	public CartResponseDTO save(Long userId, String itemId);

	/**
	 * Save order.
	 *
	 * @param user
	 *            the user
	 * @param product
	 *            the productDTO
	 * @return the order
	 */
	public Order saveOrder(User user, ProductDTO product);


	/**
	 * Update.
	 *
	 * @param order
	 *            the order
	 * @param product
	 *            the productDTO
	 * @return the order
	 */
//	public Order update(Order order, Item item);
	public Order update(Order order, ProductDTO product);


	/**
	 * Removes the order item.
	 *
	 * @param orderItemId
	 *            the order item id
	 * @return the order
	 */
	public Order removeOrderItem(Long orderItemId);

	/**
	 * Update order item.
	 *
	 * @param orderItemId
	 *            the order item id
	 * @param quantity
	 *            the quantity
	 * @return the order
	 */
	public CartResponseDTO updateOrderItem(Long orderItemId, Integer quantity);

	/**
	 * Update Subtotal price.
	 *
	 * @param order
	 *            the order
	 * @return the order
	 */
	public Order updateSubTotalPrice(Order order);

	/**
	 * Update Order.
	 *
	 * @param order
	 *            the Order
	 * @return the order
	 */
	public Order update(Order order);

	/**
	 * Update Taxation Price.
	 *
	 * @param orderId
	 *            the order id
	 * @param taxAbbrv
	 *            the tax abbrv
	 * @return the order
	 */
	public Order updateTaxationPrice(Long orderId, String taxAbbrv);

	/**
	 * Merge cart from guest user.
	 *
	 * @param guestUser
	 *            the guest user
	 * @param returningUser
	 *            the returning user
	 * @return the order
	 */
	public Order mergeCartFromGuestUser(Long guestUserId, Long returningUser);
	
	/**
	 * Gets the active CartResponseDTO by user id.
	 *
	 * @param userId
	 *            the user id
	 * @return the active order by user id
	 */
	public CartResponseDTO getCartByUserIdWithResponseDTO(Long userId);
	
	/**
	 * Removes the order item.
	 *
	 * @param orderItemId
	 *            the order item id
	 * @return the order
	 */
	public CartResponseDTO removeOrderItemReturnDTO(Long orderitemid);
}
