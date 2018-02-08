package com.nisum.fcc.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.fcc.api.controller.ProductApiController;
import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.dto.ShopProductDTO;
import com.nisum.fcc.service.ProductService;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

@WebMvcTest(value = ProductApiController.class, secure = false)
public class ProductApiControllerTest  extends BaseControllerTest {
	@MockBean 
	private ProductService productService;
	
	private final String mockProduct = 	"{\r\n" + 
			"  \"id\" : \"em1-Embellished round frame acetate and gold tone sunglasses\",\r\n" + 
			"  \"sku\" : \"nike55\",\r\n" + 
			"  \"categoryName\" : \"women-activewear\",\r\n" + 
			"  \"item\" : {\r\n" + 
			"    \"id\" : \"010456c0-bfef-11e7-ab38-372744124c42\",\r\n" + 
			"    \"name\" : \"Embellished round frame acetate and gold tone sunglasses\",\r\n" + 
			"    \"isBuyable\" : 1,\r\n" + 
			"    \"itemPrice\" : [ {\r\n" + 
			"      \"price\" : 40\r\n" + 
			"    } ],\r\n" + 
			"    \"itemImages\" : [ ],\r\n" + 
			"    \"defaultImage\" : \"http://www.valka.in/wp-content/uploads/2015/05/27.jpg\"\r\n" + 
			"  }\r\n" + 
			"}";
	private final String mockIdProduct = "{\"id\":\"em1-Embellished round frame acetate and gold tone sunglasses\",\"sku\":\"nike55\",\"categoryName\":\"women-activewear\",\"item\":{\"id\":\"090da970-b3f1-11e7-aefe-cb073bd29cf2\",\"name\":\"Embellished round frame acetate and gold tone sunglasses\",\"isBuyable\":1,\"code\":\"em1\",\"quantity\":11,\"itemPrice\":{\"price\":40},\"defaultImage\":\"http://bikermart.in/images/shop/tg1.jpg\"}}";
	
	private final String PRODUCT_API_URL_GET ="/api/rest/products/item/010456c0-bfef-11e7-ab38-372744124c42";
	private final String PRODUCT_API_ID_GET_URL = "/api/rest/products/090da970-b3f1-11e7-aefe-cb073bd29cf2";

	
	@Test
	public void findProductByItemIdTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDto = mapper.readValue(mockProduct, ProductDTO.class);
	//	Mockito.when(productService.getProductById(Mockito.anyString(),Mockito.any(Optional.class))).thenReturn(productDto);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_API_URL_GET).accept(MediaType.APPLICATION_JSON).content(mockProduct).contentType(MediaType.APPLICATION_JSON)).andReturn();

		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		JSONAssert.assertEquals(mockProduct, result.getResponse().getContentAsString(),false);
	}
	
	@Test
	public void shouldReturnProductById() {
		ObjectMapper mapper = new ObjectMapper();
		ShopProductDTO shopProductDto;
		try {
			shopProductDto = mapper.readValue(mockIdProduct, ShopProductDTO.class);
		//	Mockito.when(productService.getProductById(Mockito.anyString())).thenReturn(shopProductDto);
			MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_API_ID_GET_URL).accept(MediaType.APPLICATION_JSON).content(mockProduct).contentType(MediaType.APPLICATION_JSON)).andReturn();
			
			assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
			JSONAssert.assertEquals(mockIdProduct, result.getResponse().getContentAsString(),false);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
