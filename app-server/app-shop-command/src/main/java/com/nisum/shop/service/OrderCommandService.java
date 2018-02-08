package com.nisum.shop.service;

import com.nisum.common.shop.dto.ItemAddToCartDTO;

public interface OrderCommandService {

	public String save(Long userId, String itemId);
	
	public String save(String userId, String itemId);
	
	public String updateOrderItem(ItemAddToCartDTO item);
	
	public String removeOrderItemReturnDTO(Long orderitemid);
	
}
