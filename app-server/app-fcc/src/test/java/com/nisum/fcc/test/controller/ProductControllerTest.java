package com.nisum.fcc.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.fcc.controller.ProductController;
import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.service.ProductService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ProductController.class, secure = false)
public class ProductControllerTest extends BaseControllerTest{
	@MockBean 
	private ProductService productService;
	
	private final String PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_ID ="/products/items/010456c0-bfef-11e7-ab38-372744124c42";
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

	private final String PRODUCT_CONTROLLER_URL_GET_PRODUCT_LIST ="/products/";
	
	private final String PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_NAME_AND_CODE ="/products/c11-casual side striped trousers";
	private final String mockProductByCodeAndName = "{\r\n" + 
			"\"id\": \"c11-casual side striped trousers\",\r\n" + 
			"\"sku\": \"nike56\",\r\n" + 
			"\"categoryName\": \"women-activewear\",\r\n" + 
			"\"item\": {\r\n" + 
			"\"id\": \"010a4a30-bfef-11e7-ab38-372744124c42\",\r\n" + 
			"\"name\": \"casual side striped trousers\",\r\n" + 
			"\"isBuyable\": 1,\r\n" + 
			"\"itemPrice\": [\r\n" + 
			"  {\r\n" + 
			"\"price\": 40\r\n" + 
			"}\r\n" + 
			"],\r\n" + 
			"\"itemImages\": [],\r\n" + 
			"\"defaultImage\": \"http://www.tomtomkids.com.br/admin/modulos/upload/server/php/files/thumbnail2/Editada9%20%281%29.jpg\"\r\n" + 
			"}\r\n" + 
			"}";
	
	private final String PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_CATEGORY ="/products/categories/women-coats";
	private final String mockProductCategory = "{\r\n" + 
			"    \"id\" : \"em1-Embellished round frame acetate and gold tone sunglasses\",\r\n" + 
			"    \"sku\" : \"nike55\",\r\n" + 
			"    \"categoryName\" : \"women-activewear\",\r\n" + 
			"    \"item\" : {\r\n" + 
			"      \"id\" : \"010456c0-bfef-11e7-ab38-372744124c42\",\r\n" + 
			"      \"name\" : \"Embellished round frame acetate and gold tone sunglasses\",\r\n" + 
			"      \"isBuyable\" : 1,\r\n" + 
			"      \"itemPrice\" : [ {\r\n" + 
			"        \"price\" : 40\r\n" + 
			"      } ],\r\n" + 
			"      \"itemImages\" : [ ],\r\n" + 
			"      \"defaultImage\" : \"http://www.valka.in/wp-content/uploads/2015/05/27.jpg\"\r\n" + 
			"    }\r\n" + 
			"  }";
	
	private final String PRODUCT_CONTROLLER_URL_SEARCH_PRODUCT_BY_NAME ="/products/search?name=Embel";

	@Test
	public void findProductByIdTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDto = mapper.readValue(mockProduct, ProductDTO.class);
	//	Mockito.when(productService.getProductById(Mockito.anyString(), Mockito.any(Optional.class))).thenReturn(productDto);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("sku").value("nike55"))
				.andExpect(jsonPath("categoryName").value("women-activewear"))
				.andExpect(jsonPath("id").value("em1-Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("item.id").value("010456c0-bfef-11e7-ab38-372744124c42"))
				.andExpect(jsonPath("item.name").value("Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("item.isBuyable").value("1"))
				.andExpect(jsonPath("item.defaultImage").value("http://www.valka.in/wp-content/uploads/2015/05/27.jpg"))
				.andExpect(jsonPath("item.itemPrice[0].price").value("40"));
	}
	
	@Test(expected=Exception.class)
	public void findProductByIdEmptyTest() throws Exception {
	//	Mockito.when(productService.getProductById(Mockito.anyString(),Mockito.any(Optional.class))).thenReturn(null);
		RequestBuilder builder = MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_ID)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(builder).andReturn();
		mvc.perform(asyncDispatch(result)).andExpect(status().isNoContent());
	}

	@Test
	public void listTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDto = mapper.readValue(mockProduct, ProductDTO.class);
		List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		productDtoList.add(productDto);
		Mockito.when(productService.getAllByPage(Mockito.any(Pageable.class))).thenReturn(productDtoList);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();	
		mvc.perform(asyncDispatch(result))		
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists())
				.andExpect(jsonPath("content[0].id").value("em1-Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("content[0].sku").value("nike55"))
				.andExpect(jsonPath("content[0].categoryName").value("women-activewear"))
				.andExpect(jsonPath("content[0].item.id").value("010456c0-bfef-11e7-ab38-372744124c42"))
				.andExpect(jsonPath("content[0].item.name").value("Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("content[0].item.isBuyable").value("1"))
				.andExpect(jsonPath("content[0].item.defaultImage").value("http://www.valka.in/wp-content/uploads/2015/05/27.jpg"))
				.andExpect(jsonPath("content[0].item.itemPrice[0].price").value("40"))
				.andExpect(jsonPath("totalElements").value("1"))
				.andExpect(jsonPath("totalPages").value("1"))
				.andExpect(jsonPath("size").value("0"))
				.andExpect(jsonPath("number").value("0"))
				.andExpect(jsonPath("numberOfElements").value("1"))
				.andExpect(jsonPath("last").value("true"))
				.andExpect(jsonPath("first").value("true"));
	}
	
	@Test(expected=Exception.class)
	public void shouldThrowExceptionWhenServiceIsDown() throws Exception {
		Mockito.when(productService.getAllByPage(Mockito.any(Pageable.class))).thenThrow(new Exception());

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
        mvc.perform(asyncDispatch(result))
			.andExpect(status().isInternalServerError())
			.andExpect(jsonPath("errorCode").value("500"));
	}
	
	@Test
	public void listEmptyTest() throws Exception {
		Mockito.when(productService.getAllByPage(Mockito.any(Pageable.class))).thenReturn(new ArrayList<ProductDTO>());
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_LIST)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();	
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").isArray())
				.andExpect(jsonPath("content").isEmpty());
	}
	
	@Test
	public void findProductByNameAndCodeTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDto = mapper.readValue(mockProductByCodeAndName, ProductDTO.class);
		Mockito.when(productService.findByUniqueIdentifier(Mockito.anyString())).thenReturn(productDto);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_NAME_AND_CODE)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("sku").value("nike56"))
				.andExpect(jsonPath("categoryName").value("women-activewear"))
				.andExpect(jsonPath("id").value("c11-casual side striped trousers"))
				.andExpect(jsonPath("item.id").value("010a4a30-bfef-11e7-ab38-372744124c42"))
				.andExpect(jsonPath("item.name").value("casual side striped trousers"))
				.andExpect(jsonPath("item.isBuyable").value("1"))
				.andExpect(jsonPath("item.defaultImage").value("http://www.tomtomkids.com.br/admin/modulos/upload/server/php/files/thumbnail2/Editada9%20%281%29.jpg"))
				.andExpect(jsonPath("item.itemPrice[0].price").value("40"));
	}
	
	@Test(expected=Exception.class)
	public void findProductByNameAndCodeEmptyTest() throws Exception {
		Mockito.when(productService.findByUniqueIdentifier(Mockito.anyString())).thenReturn(null);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_NAME_AND_CODE)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isNotFound());
	}
	
	@Test
	public void getProductByCategoryTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		ProductDTO productDto = mapper.readValue(mockProductCategory, ProductDTO.class);
		List<ProductDTO> productDtoList = new ArrayList<ProductDTO>();
		productDtoList.add(productDto);
		Page<ProductDTO> productDtoPage = new PageImpl<ProductDTO>(productDtoList);
		Mockito.when(productService.getProductByCategory(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(productDtoPage);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_CATEGORY)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		result.getAsyncResult(10000);
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath("content").exists())
				.andExpect(jsonPath("content[0].id").value("em1-Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("content[0].sku").value("nike55"))
				.andExpect(jsonPath("content[0].categoryName").value("women-activewear"))
				.andExpect(jsonPath("content[0].item.id").value("010456c0-bfef-11e7-ab38-372744124c42"))
				.andExpect(jsonPath("content[0].item.name").value("Embellished round frame acetate and gold tone sunglasses"))
				.andExpect(jsonPath("content[0].item.isBuyable").value("1"))
				.andExpect(jsonPath("content[0].item.defaultImage").value("http://www.valka.in/wp-content/uploads/2015/05/27.jpg"))
				.andExpect(jsonPath("content[0].item.itemPrice[0].price").value("40"))
				.andExpect(jsonPath("totalElements").value("1"))
				.andExpect(jsonPath("totalPages").value("1"))
				.andExpect(jsonPath("size").value("0"))
				.andExpect(jsonPath("number").value("0"))
				.andExpect(jsonPath("numberOfElements").value("1"))
				.andExpect(jsonPath("last").value("true"))
				.andExpect(jsonPath("first").value("true")).andReturn();
	}
	
	@Test(expected=Exception.class)
	public void getProductByCategoryEmptyTest() throws Exception {
		Mockito.when(productService.getProductByCategory(Mockito.anyString(),Mockito.any(Pageable.class))).thenReturn(null);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_GET_PRODUCT_BY_CATEGORY)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		mvc.perform(asyncDispatch(result))		
				.andExpect(status().isNoContent());
	}

	@Test
	public void searchProductByNameTest() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		List<ProductDTO> list = new ArrayList<>();
		ProductDTO productDto = mapper.readValue(mockProduct, ProductDTO.class);
		list.add(productDto);
		Mockito.when(productService.searchProductByName(Mockito.anyString())).thenReturn(list);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_SEARCH_PRODUCT_BY_NAME)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON))
				.andReturn();
		
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk());				
	}
	
	@Test(expected=Exception.class)
	public void searchProductByNameEmptyTest() throws Exception {
		Mockito.when(productService.searchProductByName(Mockito.anyString())).thenReturn(null);
		RequestBuilder builder = MockMvcRequestBuilders.get(PRODUCT_CONTROLLER_URL_SEARCH_PRODUCT_BY_NAME)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(builder).andReturn();
		mvc.perform(asyncDispatch(result)).andExpect(status().isNoContent());
	}
	
}
