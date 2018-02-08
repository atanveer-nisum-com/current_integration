package com.nisum.promotion.test.services;

import com.nisum.promotion.dto.CategoryPromotion;
import com.nisum.promotion.repository.CategoryDiscountRepository;
import com.nisum.promotion.service.CategoryDiscountService;
import com.nisum.promotion.service.impl.CategoryDiscountServiceImpl;
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
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DiscountServiceTest extends BaseServiceTest {
	
	@Autowired
	private CategoryDiscountService categoryDiscountService;
	
	@MockBean
	private CategoryDiscountRepository categoryDiscountRepository;

	@Configuration
	static class ContextConfiguration {

		@Bean
		CategoryDiscountService categoryDiscountService() {
			return new CategoryDiscountServiceImpl();
		}
	}

	@Test
	public void getCategoryDiscount() {
		
		//Get list of Category Promotions objects return to MockRequest
		List<CategoryPromotion> listCategoryPromotion= MockModelsFactory.getListPromotionCategory();
		assertNotNull(listCategoryPromotion);
		
		//Test when any Set of String come should return Category discounts list 
		Mockito.when(categoryDiscountRepository.getDiscountByCategories(Matchers.<Set<String>>any())).thenReturn(listCategoryPromotion);
		
		// Assert
		assertEquals(listCategoryPromotion, categoryDiscountService.getDiscountByCategories(Matchers.<Set<String>>any()));

	}
	
	@Test(expected=EmptyResultDataAccessException.class)
	public void shouldThrownExceptionOnCategoryDiscount() {
		
		Mockito.when(categoryDiscountRepository.getDiscountByCategories(Matchers.<Set<String>>any())).thenReturn(null);
		
		// Assert
		categoryDiscountService.getDiscountByCategories(null);

	}
	

}
