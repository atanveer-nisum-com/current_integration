/**
 * 
 */
package com.nisum.promotion.test.controller;

import com.nisum.common.exception.rest.RestException;
import com.nisum.promotion.controller.DiscountController;
import com.nisum.promotion.dto.CategoryPromotion;
import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;
import com.nisum.promotion.service.CategoryDiscountService;
import com.nisum.promotion.service.OrderDiscountService;
import com.nisum.promotion.service.ProductDiscountService;
import com.nisum.promotion.test.dataProvider.DiscountCategoryControllerDataProviderTest;
import com.nisum.promotion.test.dataProvider.DiscountControllerDataProviderTest;
import com.nisum.promotion.test.mock.models.MockModelsFactory;
import org.junit.Test;
import org.mockito.Matchers;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Matchers.anyDouble;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


/**
 * @author Nisum Pakistan
 * 
 * Test Case for Mock Service Product Discount
 *
 */
@WebMvcTest(value = DiscountController.class, secure = false)
public class DiscountControllerTest extends BaseControllerTest {


	
	@MockBean
	private OrderDiscountService orderDiscountService;
	@MockBean 
	private ProductDiscountService productDiscountService;
	@MockBean
	private CategoryDiscountService categoryDiscountService;
	
	
	private static final String GET_PRODUCTS_PROMOTIONS_URL = DiscountControllerDataProviderTest.getPromotion_URL();
	private static final String[] ProductIDs=DiscountControllerDataProviderTest.getProductIDs();

	private static final String GET_CATEGORY_PROMOTIONS_URL = DiscountCategoryControllerDataProviderTest.getPromotion_URL();
	private static final String GET_ORDER_DISCOUNT_URL = DiscountControllerDataProviderTest.getOrder_discount_url();
	private static final String[] CATEGORIES_NAME = DiscountCategoryControllerDataProviderTest.getCategoriesName();
	private static final String GET_CATEGORIES_PRODUCTS_PROMOTIONS_URL = DiscountControllerDataProviderTest.getCategories_product_promotion_URL();
	
	@Test
	public 	void ShouldReturnProductPromotions() {
		
		//Get list of Product Promotions objects return to MockRequest
		List<ProductPromotion> ListProductPromotion= MockModelsFactory.getListPromotionProducts();
		assertNotNull(ListProductPromotion);
		//Test when any String array come should return Products 
		when(productDiscountService.getDiscountByProducts(Matchers.<String[]>any())).thenReturn(ListProductPromotion);
				
		RequestBuilder 
		requestBuilder = MockMvcRequestBuilders
		.get(GET_PRODUCTS_PROMOTIONS_URL+ProductIDs)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON);
		
		//Perform mock Spring MVC test.
		MvcResult result;
		try {
			result = mvc.perform(requestBuilder).andExpect(request().asyncStarted()).andReturn();
			mvc.perform(asyncDispatch(result)).andExpect(status().isOk())
					.andExpect(jsonPath(DiscountControllerDataProviderTest.getJsonPathAttribute())
							.value(DiscountControllerDataProviderTest.getJsonPathVerifyValue())).andReturn();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldThrowExceptionWhenNoProductsPromotionFound() {
		when(productDiscountService.getDiscountByProducts(Matchers.<String[]>any()))
				.thenThrow(new EmptyResultDataAccessException(1));

        MvcResult result = null;
        try {
            result = mvc.perform(MockMvcRequestBuilders
                    .get(GET_PRODUCTS_PROMOTIONS_URL + Arrays.toString(ProductIDs))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(request().asyncStarted())
                    .andExpect(request().asyncResult(instanceOf(RestException.class)))
                    .andReturn();
        mvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
        } catch (Exception e) {
            if (e.getCause() instanceof RestException) {
                assertEquals(((RestException) e.getCause()).getHttpStatus(), HttpStatus.NOT_FOUND);
            }
        }

	}
	
	@Test
	public 	void ShouldReturnCategoryProductPromotions() throws Exception {
		
		//Get list of Category Promotions objects return to MockRequest
		List<CategoryPromotion> listCategoryPromotion= MockModelsFactory.getListPromotionCategory();
		assertNotNull(listCategoryPromotion);
		//Test when any String array come should return Category default discount 
		when(categoryDiscountService.getDiscountByCategories(Matchers.<Set<String>>any())).thenReturn(listCategoryPromotion);
		
		RequestBuilder 
		requestBuilder = MockMvcRequestBuilders
		.get(GET_CATEGORY_PROMOTIONS_URL+CATEGORIES_NAME)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON);
		
		//Perform mock Spring MVC test.
		MvcResult  result =mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
		.andExpect(status().isOk())
		.andExpect(jsonPath(DiscountCategoryControllerDataProviderTest.getJsonPathAttribute()).value(DiscountCategoryControllerDataProviderTest.getJsonPathVerifyValue()));
			
	}

	@Test
	public void shouldThrowExceptionWhenNoCategoryPromotionFound() {
		when(categoryDiscountService.getDiscountByCategories(Matchers.<Set<String>>any()))
				.thenThrow(new EmptyResultDataAccessException(1));

		try {
			MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .get(GET_CATEGORY_PROMOTIONS_URL + Arrays.toString(CATEGORIES_NAME))
                    .accept(MediaType.APPLICATION_JSON)
                    .contentType(MediaType.APPLICATION_JSON)).andExpect(request().asyncStarted()).andReturn();
			mvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
		} catch (Exception e) {
            if (e.getCause() instanceof RestException) {
                assertEquals(((RestException) e.getCause()).getHttpStatus(), HttpStatus.NOT_FOUND);
            }
		}
	}
	
	@Test
	public 	void ShouldReturnOrderDiscount() throws Exception {
				
		//Test when any Double array come should return Category discount amount 
		when(orderDiscountService.getOrderDiscount(anyDouble())).thenReturn(DiscountControllerDataProviderTest.getOrderDisount());
		
		RequestBuilder 
		requestBuilder = MockMvcRequestBuilders
		.get(GET_ORDER_DISCOUNT_URL)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON);
		
		//Perform mock Spring MVC test.
		MvcResult  result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
		.andExpect(status().isOk());
		Double a = DiscountControllerDataProviderTest.getOrderDisount();
		Boolean Returncondition= Double.parseDouble(result.getAsyncResult().toString())==DiscountControllerDataProviderTest.getOrderDisount();
		assertTrue(Returncondition);
			
			
	}

	@Test
	public void shouldThrowExceptionWhenNoOrderDiscountFound() {
		when(orderDiscountService.getOrderDiscount(anyDouble())).thenThrow(new EmptyResultDataAccessException(1));

		MvcResult result = null;
		try {
			result = mvc.perform(MockMvcRequestBuilders.get(GET_ORDER_DISCOUNT_URL)
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
			mvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
		} catch (Exception e) {
            if (e.getCause() instanceof RestException) {
                assertEquals(((RestException) e.getCause()).getHttpStatus(), HttpStatus.NOT_FOUND);
            }
		}
	}
	
	@Test
	public 	void ShouldReturnPromotionsByProductAndCategory() throws Exception {
		
		//Get list of Product Promotions objects return to MockRequest
		List<ProductCategoryPromotion> ListCategoriesProductPromotion= MockModelsFactory.getListPromotionCategoriesProducts();
		assertNotNull(ListCategoriesProductPromotion);
		//Test when any String array come should return Products 
		when(productDiscountService.getDiscountByProductAndCategories(Matchers.<String[]>any())).thenReturn(ListCategoriesProductPromotion);
				
		RequestBuilder 
		requestBuilder = MockMvcRequestBuilders
		.get(GET_CATEGORIES_PRODUCTS_PROMOTIONS_URL+ProductIDs)
		.accept(MediaType.APPLICATION_JSON)
		.contentType(MediaType.APPLICATION_JSON);
		
		//Perform mock Spring MVC test.
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
				.andExpect(jsonPath(DiscountControllerDataProviderTest.getJsonPathAttribute()).value(DiscountControllerDataProviderTest.getJsonPathVerifyValue()));
			
	}

	@Test
	public void shouldThrowExceptionWhenNoPromotionByProductAndCategoryFound() {
		when(productDiscountService.getDiscountByProductAndCategories(Matchers.<String[]>any()))
				.thenThrow(new EmptyResultDataAccessException(1));

		try {
			MvcResult result = mvc.perform(MockMvcRequestBuilders
                    .get(GET_CATEGORIES_PRODUCTS_PROMOTIONS_URL + Arrays.toString(ProductIDs))
                    .accept(MediaType.APPLICATION_JSON).contentType(MediaType.APPLICATION_JSON)).andReturn();
			mvc.perform(asyncDispatch(result)).andExpect(status().isNotFound());
		} catch (Exception e) {
            if (e.getCause() instanceof RestException) {
                assertEquals(((RestException) e.getCause()).getHttpStatus(), HttpStatus.NOT_FOUND);
            }
		}
	}

}
