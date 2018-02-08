package com.nisum.shop.service;


import com.nisum.shop.dto.OrderDTO;
import com.nisum.shop.dto.UserOrderDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Interface UserOrderService.
 */
public interface UserOrderService {

	/**
	 * Gets the order history by user id.
	 *
	 * @param orderHistory the order history
	 * @return the order history by user id
	 */
	public Page<OrderDTO> getOrderHistoryByUserId(UserOrderDTO orderHistory, Pageable page);

	/**
	 * Gets the order by user id and order id.
	 *
	 * @param orderHistory the order history
	 * @return the order by user id and order id
	 */
	public OrderDTO getOrderByUserIdAndOrderId(UserOrderDTO orderHistory);

	/**
	 * Cancel unshipped order.
	 *
	 * @param orderHistory the order history
	 * @return the order
	 */
	public OrderDTO cancelUnshippedOrder(UserOrderDTO orderHistory);
}
