package com.nisum.shop.util;

import com.nisum.shop.dto.OrderItemDTO;
import com.nisum.shop.model.OrderItem;

/**
 * The Class ETDUtils.
 */
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
//	public static Set<OrderItemDTO> toOrderItemDTO(Set<OrderItem> orderItemSet,List<ProductDTO> productDTOs){
//		int i=0;
//		Set<OrderItemDTO> orderDTOSet= new HashSet<OrderItemDTO>();
//		for(OrderItem order: orderItemSet){
//			OrderItemDTO orderItemDto = new OrderItemDTO(order.getId(), order.getInventoryStatus(), order.getPrice(), 
//					order.getSubTotalPrice(), order.getQuantity(), order.getTaxation(), order.getTotalPrice(),productDTOs.get(i++));
//			orderDTOSet.add(orderItemDto);
//		}
//		return orderDTOSet;
//		
//	}


}
