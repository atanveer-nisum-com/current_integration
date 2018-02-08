package com.nisum.promotion.test.services;

import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;
import com.nisum.promotion.repository.ProductDiscountRepository;
import com.nisum.promotion.service.ProductDiscountService;
import com.nisum.promotion.service.impl.ProductDiscountServiceImpl;
import com.nisum.promotion.test.dataProvider.DiscountControllerDataProviderTest;
import com.nisum.promotion.test.mock.models.MockModelsFactory;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;

import static org.junit.Assert.*;

public class ProductDiscountServiceTest extends BaseServiceTest {

	@Autowired
	private ProductDiscountService productDiscountService;
	
	@MockBean
	private ProductDiscountRepository productDiscountRepository;

	@Configuration
	static class ContextConfiguration {

		@Bean
		ProductDiscountService productDiscountService() {
			return new ProductDiscountServiceImpl();
		}
	}

	@Test
	public void shouldGetDiscountByProducts() throws Exception {

		// Get the array of ProductIds
		String[] productIds = DiscountControllerDataProviderTest.getProductIDs();
		assertNotNull(productIds);

		// Get list of Product Promotions objects return to MockRequest
		List<ProductPromotion> productPromotion = MockModelsFactory.getListPromotionProducts();
		assertNotNull(productPromotion);

		//Test when any array of Strings come should return list of products for promotion
		Mockito.when(productDiscountRepository.findDiscountByProductIds(Matchers.<String[]>any()))
			.thenReturn(productPromotion);

		
		assertEquals(productPromotion, productDiscountService.getDiscountByProducts(Matchers.<String[]>any()));

	}
	
	
	@Test(expected = EmptyResultDataAccessException.class)
	public void shouldGetException() {

		// Get the array of ProductIds
		String[] productIds = DiscountControllerDataProviderTest.getProductIDs();
		assertNotNull(productIds);

		// Get list of Product Promotions objects return to MockRequest
		List<ProductPromotion> productPromotion = MockModelsFactory.getNullListPromotionProducts();
		assertNull(productPromotion);

		productDiscountService.getDiscountByProducts(null);

	}
	
	@Test
	public void shouldGetDiscountByCategoriesAndProducts() {

		// Get the array of ProductIds
		String[] productIds = DiscountControllerDataProviderTest.getProductIDs();
		assertNotNull(productIds);

		// Get list of Product Promotions objects return to MockRequest
		List<ProductCategoryPromotion> productPromotion = MockModelsFactory.getListPromotionCategoriesProducts();
		assertNotNull(productPromotion);

		//Test when any array of Strings come should return list of products for promotion
		Mockito.when(productDiscountRepository.findDiscountByProductAndCategoryIds(Matchers.<String[]>any()))
			.thenReturn(productPromotion);

		
		assertEquals(productPromotion, productDiscountService.getDiscountByProductAndCategories(Matchers.<String[]>any()));

	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrowExceptionOnGetDiscountByCategoriesAndProducts() {
		Mockito.when(productDiscountRepository.findDiscountByProductAndCategoryIds(null))
		.thenReturn(null);
		productDiscountService.getDiscountByProductAndCategories(null);
	}

}
