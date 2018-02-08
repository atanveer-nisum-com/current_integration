package com.nisum.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.common.shop.dto.ItemAddToCartDTO;
import com.nisum.shop.service.OrderCommandService;


/**
 * 
 * @author omussawir
 *
 */
@RestController
@RequestMapping("cart")
public class CartCommandController extends BaseController{
	
	@Autowired
	private OrderCommandService orderServiceCommand;
	
	//add to cart
	@PostMapping("/items/{itemId}")
	public String saveOrder(@PathVariable String itemId,@RequestHeader("userId") String userId) {
		return orderServiceCommand.save("null".equals(userId)? null : Long.valueOf(userId), itemId);
	}
	
	@PutMapping("/items/{orderItemId}")
	public String updateOrderItem(@PathVariable Long orderItemId, @RequestBody ItemAddToCartDTO item) {
		ItemAddToCartDTO itemAddToCartDTO = new ItemAddToCartDTO();
		itemAddToCartDTO.setOrderItemId(orderItemId);
		itemAddToCartDTO.setQuantity(item.getQuantity());
		return orderServiceCommand.updateOrderItem(itemAddToCartDTO);
	}
	
	@DeleteMapping("/items/{orderitemid}")
	public String delete(@PathVariable Long orderitemid){
		return orderServiceCommand.removeOrderItemReturnDTO(orderitemid);
	}
}
