package com.nisum.shop.controller;

import com.nisum.shop.dto.OrderDTO;
import com.nisum.shop.dto.TaxStateDTO;
import com.nisum.shop.dto.UserOrderDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.service.UserOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

/**
 * The class {@code OrderController} includes CRUD operation methods 
 *  
 * @author nisum pakistan
 */

@RestController
@RequestMapping("orders")
public class OrderController extends BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

	/** The order service. */
	@Autowired
	private OrderService orderService;
	
	/** The order history service. */
	@Autowired
	private UserOrderService userOrderService;

	
	/**
	 * Gets the order history.
	 *
	 * @param userOrderDTO the user order DTO
	 * @return the order history
	 */
	@GetMapping()
	public Page<OrderDTO> getOrderHistory(@RequestHeader("userId") Long userId, @PageableDefault(size = PAGE_SIZE_12) Pageable page){
		UserOrderDTO userOrderDTO = new UserOrderDTO();
		userOrderDTO.setUserId(userId);
		return userOrderService.getOrderHistoryByUserId(userOrderDTO, page);
	}

	/**
	 * Find order by user id and order id.
	 *
	 * @param userOrderDTO the user order DTO
	 * @return the list
	 */
	@GetMapping("/{orderId}")
	public OrderDTO findOrderByUserIdAndOrderId(@RequestHeader("userId") Long userId,@PathVariable("orderId") Long orderId){
		
		UserOrderDTO userOrderDTO = new UserOrderDTO();
		userOrderDTO.setUserId(userId);
		userOrderDTO.setOrderId(orderId);
		
		return userOrderService.getOrderByUserIdAndOrderId(userOrderDTO);	
	}

	/**
	 * Cancel unshipped order.
	 *
	 * @param userOrderDTO the user order DTO
	 * @return the order
	 */
	@PutMapping("/{orderId}")
	public OrderDTO cancelUnshippedOrder(@RequestHeader("userId") Long userId,@PathVariable("orderId") Long orderId){
		
		UserOrderDTO userOrderDTO = new UserOrderDTO();
		userOrderDTO.setUserId(userId);
		userOrderDTO.setOrderId(orderId);
		
		return userOrderService.cancelUnshippedOrder(userOrderDTO);
	}
	
	/**
	 * This method sends a call to orderService method updateTaxationPrice which
	 * updates the tax value and order total price of order.
	 *
	 * @param orderId the order id
	 * @param abbrv the abbrv
	 * @return 	success
	 */
	@PutMapping("/{orderId}/tax")
	public boolean updateTaxation(@PathVariable Long orderId, @RequestBody TaxStateDTO taxStateDTO) {
		Order order = orderService.updateTaxationPrice(orderId, taxStateDTO.getAbbrv());
		if((order.getOrderTotalPrice() != null) && (order.getTaxation() != null)) {
			return true;
		} else {
			return false;
		}
	}
	
}
