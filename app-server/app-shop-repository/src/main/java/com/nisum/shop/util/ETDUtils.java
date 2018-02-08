package com.nisum.shop.util;

import com.nisum.common.shop.dto.OrderItemDTO;
import com.nisum.shop.model.OrderItem;

public class ETDUtils {
	
	public static OrderItemDTO toOrderItemDTO(OrderItem orderItem) {
		OrderItemDTO ordertemDto = new OrderItemDTO();
		ordertemDto.setId(orderItem.getId());
		ordertemDto.setInventoryStatus(orderItem.getInventoryStatus());
		ordertemDto.setPrice(orderItem.getPrice());
		ordertemDto.setSubTotalPrice(orderItem.getSubTotalPrice());
		ordertemDto.setQuantity(orderItem.getQuantity());
		ordertemDto.setTaxation(orderItem.getTaxation());
		ordertemDto.setTotalPrice(orderItem.getTotalPrice());
		return ordertemDto;
		
	}

}
