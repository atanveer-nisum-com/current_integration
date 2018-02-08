package com.nisum.shop.util;

import com.nisum.common.util.Converter;
import com.nisum.shop.dto.OrderItemDTO;
import com.nisum.shop.model.OrderItem;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter implements Converter<OrderItem, OrderItemDTO>{

	@Override
	public OrderItemDTO convertToDto(OrderItem entity) {
		return ETDUtils.toOrderItemDTO(entity);
	}

	@Override
	public OrderItem convertToEntity(OrderItemDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Page<OrderItemDTO> convertEntitiesToDtos(Page<OrderItem> entities) {
		// TODO Auto-generated method stub
		return null;
	}

}
