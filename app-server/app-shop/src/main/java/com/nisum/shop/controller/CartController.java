package com.nisum.shop.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.dto.ItemAddToCartDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.User;
import com.nisum.shop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;


/**
 * The Class CartController.
 */
@RestController
@RequestMapping("cart")
public class CartController extends BaseController {
	
	/** The order service. */
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private ExecutorService executorService;

	/**
	 * Gets the item count.
	 *
	 * @param userId the user id
	 * @return the item count
	 */
	@GetMapping("/itemcount")
	public Map<String, Integer> getItemCount(@RequestHeader("userId") String userId) {
		Map<String, Integer> itemCount = new HashMap<>();
		Order order = orderService.getActiveOrderByUserId(Long.valueOf(userId));
		if(order != null) {
//			Integer count =  orderItemService.getOrderItemsCount(order.getId());
			Integer count =  order.getOrderItems().size();
			itemCount.put("itemCount", count);
		} else {
			itemCount.put("itemCount", 0);
		}

		return itemCount;
	}
	
	/**
	 * Method for getting Order from <code>orderService</code> against user.
	 * 
	 * 
	 * @headerParam userid the userId
	 * @return the CartDTO
	 */
	@GetMapping
	public DeferredResult<CartResponseDTO> getCart(@RequestHeader("userId") String userId) {
		DeferredResult<CartResponseDTO> deferredResult = new DeferredResult<>();

		executorService.execute(() -> {
			
			try {
				CartResponseDTO cartResponse = orderService.getCartByUserIdWithResponseDTO("".equals(userId)? null : Long.valueOf(userId));
				deferredResult.setResult(cartResponse);
				
			} catch(RestException re) {
				deferredResult.setErrorResult(re);
			}
		});
		return deferredResult;
		
	}

	
	/**
	 * This method gets id of {@link User} and id of {@link Item} as parameter and
	 * generates an {@link Order} by sending it to orderService.
	 *
	 * @param userId the user id
	 * @param itemId the item id
	 * @return Order
	 */
	@PostMapping("/items/{itemId}")
	public CartResponseDTO saveOrder(@PathVariable String itemId,@RequestHeader("userId") String userId) {
		CartResponseDTO cartResponse =  orderService.save("null".equals(userId)? null : Long.valueOf(userId), itemId);
		return cartResponse;
	}
	
	/**
	 * This method gets id of {@link OrderItem} and quantity of item as parameter and
	 * generates an {@link CartDTO} by sending it to orderService.
	 *
	 * @param orderItemId the orderItem id
	 * @param quantity the quantity
	 * @return CartDTO
	 */
	@PutMapping("/items/{orderItemId}")
	public DeferredResult<CartResponseDTO> updateOrderItem(@PathVariable Long orderItemId, @RequestBody ItemAddToCartDTO item) {
		DeferredResult<CartResponseDTO> deferredResult = new DeferredResult<>();
		executorService.execute(() -> {
			
			try {
				if (item.getQuantity() < 0)
					throw new RestException(HttpStatus.NOT_ACCEPTABLE, "Quantity can not be negative", ErrorLevel.ERROR);
				CartResponseDTO cartResponse = orderService.updateOrderItem(orderItemId, item.getQuantity());
				deferredResult.setResult(cartResponse);
				
			} catch(RestException re) {
				deferredResult.setErrorResult(re);
			}
		});
		return deferredResult;
	}
	
	
	@DeleteMapping("/items/{orderitemid}")
	public DeferredResult<CartResponseDTO> delete(@PathVariable Long orderitemid){

		DeferredResult<CartResponseDTO> deferredResult = new DeferredResult<>();
		executorService.execute(() -> {
			
			try {
				CartResponseDTO cartResponse = orderService.removeOrderItemReturnDTO(orderitemid);
				deferredResult.setResult(cartResponse);
				
			} catch(RestException re) {
				deferredResult.setErrorResult(re);
			}
		});
		return deferredResult;

	}
}
