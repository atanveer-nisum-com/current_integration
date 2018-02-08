package com.nisum.shop.test.controller;

import com.nisum.shop.controller.CartController;
import com.nisum.shop.dto.CartResponseDTO;
import com.nisum.shop.model.Order;
import com.nisum.shop.service.OrderItemService;
import com.nisum.shop.service.OrderService;
import com.nisum.shop.test.mock.models.MockModelsFactory;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = CartController.class, secure = false)
public class CartControllerTest extends BaseControllerTest {

	@MockBean
	private OrderService orderService;

	@MockBean
	private OrderItemService itemService;

	private static final String BASE_URL = "/api/v1/cart";
	private static final String GET_ITEM_COUNT_URL = BASE_URL + "/itemcount";
	private static final String GET_CART_URL = BASE_URL ;
	private static final String RESPONSE_ITEM_COUNT = "{\"itemCount\": 1}";
	
	private static final String CART_RESPONSE = "{\"id\": 2,"
			+ "\"totalItem\": 1,"
			+ "\"subTotalPrice\": 100,"
			+ "\"taxation\": 10.25,"
			+ "\"orderTotalPrice\": 110.25,"
			+ "\"orderItems\": [  {\"id\": 2,"
			+ 			"\"inventoryStatus\": null,"
			+ 			"\"price\": 100,"
			+ 			"\"subTotalPrice\": 100,"
			+ 			"\"quantity\": 1,"
			+ 			"\"taxation\": null,"
			+ 			"\"totalPrice\": null,"
			+ 			"\"product\": {\"id\": \"N11-Nike Free RN Flyknit 2017\","
			+ 				"\"sku\": \"nike55\","
			+ 				"\"categoryName\": \"women-activewear\","
			+ 				"\"item\": {\"id\": \"e6eb0560-c55a-11e7-be09-ad20fe3e430b\","
			+ 				"\"name\": \"Nike Free RN Flyknit 2017\","
			+ 				"\"isBuyable\": 1,"
			+ 				"\"defaultImage\": \"https://smartdoko.com/assets/upload/images/product/front/IMG-8417ed38a75ebfb0657aa654c8cdcc7b.jpg\","
			+ 				"\"price\": 100,"
			+ 				"\"itemImages\": []}"
			+ 						"}"
			+ 				"}]}";


	@Test
	public void shouldReturnItemCount() {
		Order testOrder = MockModelsFactory.getMockOrder();
		assertNotNull(testOrder);
		when(orderService.getActiveOrderByUserId(anyLong())).thenReturn(testOrder);
		
		//when(itemService.getOrderItemsCount(testOrder.getId())).thenReturn(testOrder.getTotalItem());
		try {
			mvc.perform(get(GET_ITEM_COUNT_URL).header("userId", "3").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Ignore
	@Test
	public void shouldReturnCart(){
		CartResponseDTO mockCart = MockModelsFactory.getMockCartDTO();
		when(orderService.getCartByUserIdWithResponseDTO(1L)).thenReturn(mockCart);
		
		try {
			mvc.perform(get(GET_CART_URL).header("userId", "1").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk()).andExpect(content().json(CART_RESPONSE, false));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void shouldReturnNotFound404() {
		try {
			mvc.perform(get("").accept(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isNotFound());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
