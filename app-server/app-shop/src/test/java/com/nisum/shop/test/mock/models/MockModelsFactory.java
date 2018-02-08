package com.nisum.shop.test.mock.models;

import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.dto.CheckoutDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.model.OrderItem;

import java.util.HashSet;
import java.util.Set;


public final class MockModelsFactory {

	private MockModelsFactory() {}
	
	public static Order getMockOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setOrderItems(getMockOrderitems());
		order.setTotalItem(3);
		return order;
	}
	
	public static CheckoutDTO getMockCheckoutDTO() {
		return new CheckoutDTO();
	}
	
	public static CartResponseDTO getMockCartDTO() {
		return new CartResponseDTO();
	}
	
	private static Set<OrderItem> getMockOrderitems(){
		Set<OrderItem> orderitem = new HashSet<>();
		
		OrderItem o = new OrderItem();
		o.setId(1L);
		
		orderitem.add(o);
		return orderitem;
		
	}
}
