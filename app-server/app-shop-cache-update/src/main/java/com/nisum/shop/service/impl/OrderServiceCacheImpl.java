package com.nisum.shop.service.impl;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.google.gson.Gson;
import com.nisum.common.constant.AppConstant;
import com.nisum.common.constant.EventTypeEnum;
import com.nisum.common.service.SaveCacheService;
import com.nisum.common.shop.controller.dto.CartLineItem;
import com.nisum.common.shop.dto.ProductDTO;
import com.nisum.common.util.BaseDTO;
import com.nisum.common.util.DateUtils;
import com.nisum.common.util.Utils;
import com.nisum.shop.cache.model.Order;
import com.nisum.shop.cache.model.OrderItem;
import com.nisum.shop.service.ConsumerCacheService;
import com.nisum.shop.service.OrderServiceCache;



/**
 * The class {@code OrderServiceImpl} implements OrderService Interface
 * 
 * @author mabdullah
 * 
 */

@Service
public class OrderServiceCacheImpl implements OrderServiceCache {

	@Autowired
	OrderServiceCache orderService;

	@Autowired
	SaveCacheService cacheRepository;

	@Autowired
	RestTemplate restClient;

	@Autowired
	Gson gson;
	
	@Override
	public void save(BaseDTO base) {
		
		
		CartLineItem cartLineItem = (CartLineItem) base;	
		String key = cartLineItem.getUserId().toString() + "-" + EventTypeEnum.CART_EVENT.getKey();
		ProductDTO productDto = restClient.getForObject("http://localhost:8081/api/rest/products/item/"+cartLineItem.getItemId()+"?iscart=1", ProductDTO.class,cartLineItem.getItemId() );
		String orderString = cacheRepository.getFromCache(key);
		//orderString = orderString.replaceAll("\", "");
		
		Order order = null;
		if(orderString == null){
	//	Order order = gson.fromJson(orderString, Order.class);
			
		 order = new Order();
		Set<OrderItem> orderItems = new HashSet<OrderItem>(); 
		 order.setOrderItems(orderItems);
		 
		}
		else{
	//	order = Utils.convertJsonToObject(orderString ,Order.class );
			orderString = orderString.replaceAll("\\\\", "");
			orderString = orderString.substring(1, orderString.length()-1);
			order = gson.fromJson(orderString , Order.class);
			//order = gson.fromJson(orderString.replaceAll("\\\\", "").substring(1, orderString.replaceAll("\\\\", "").length()-1), Order.class);
		}
//		TODO: extract this method from model
     	order = order.addOrderItem(order, cartLineItem.getItemId());
     	order = this.saveOrderLocal(order,cartLineItem.getUserId() );
     	OrderItem item=this.saveOrderItem(order, productDto);
     	order.getOrderItems().add(item);
     	//fcc hit
     	
     	
     	orderString = Utils.convertObjectToJson(order);
     //	orderString = gson.toJson(order);
     	cacheRepository.saveToCache(key, orderString);
		System.out.println("Saved to cache");
		

	}

	@Override
	public void update(BaseDTO baseDTO) {
		//TODO: to be implemented
	}

	@Override
	public void delete(BaseDTO baseDTO) {
		//TODO: to be implemented
	}

	private OrderItem saveOrderItem(Order order, ProductDTO item) {
		OrderItem orderItem = new OrderItem();
		orderItem.setQuantity(1);
	//	orderItem.setOrder(order);
		orderItem.setItemUUID(item.getItem().getId());
		orderItem = this.setPricesAndTaxationLocal(item, orderItem);
		orderItem.setCreatedAt(DateUtils.getCurrentTime());
		orderItem.setUpdatedAt(DateUtils.getCurrentTime());
		orderItem.setIsDeleted(AppConstant.NOT_DELETED);
		return orderItem;
	}
//	private ProductDTO getProductDTOLocal(){
//		ProductDTO product = new ProductDTO();
//		
//		product.setId(new Random().toString());
//		
//		return product;
//	}
//
//	
	public Order saveOrderLocal(Order order,Long userId) {
		
		order.setUserId(userId);
		order.setCreatedAt(Calendar.getInstance().getTimeInMillis());
		order.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		order.setTotalItem(1);
				
		return order;
	}
//
//	

	private OrderItem setPricesAndTaxationLocal(ProductDTO item, OrderItem orderItem) {
		if (orderItem.getQuantity() == 0) {
			return orderItem;
		}
		orderItem.setPrice(item.getItem().getPrice());
		orderItem.setSubTotalPrice(orderItem.getPrice().multiply(new BigDecimal(orderItem.getQuantity())));
		orderItem.setUpdatedAt(Calendar.getInstance().getTimeInMillis());
		return orderItem;
	}
//	
//	public User findOneByIdLocal(Long userId){
//		User user = new User();
//		
//		return user;
//	}
//	
//	public Order getActiveOrderByUserIdLocal(Long userId) {
//		return new Order();
//	}
//	
//	public OrderItem getOrderItemByOrderIdAndItemUUIdLocal(Long orderId, String itemId) {
//		return new OrderItem();
//	}

}
