package com.nisum.promotion.test.mock.models;

import com.nisum.promotion.dto.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nisum Pakistan
 * 
 * Test Case for Mock Service Product Discount
 *
 */
public final class MockModelsFactory {

	private MockModelsFactory() {}
	
	public static List<ProductPromotion> getListPromotionProducts() {
		 List<ProductPromotion> ProductPromotionListInstance = new ArrayList<ProductPromotion>();
		 ProductPromotionDTO ProductPromotionInstance=new ProductPromotionDTO();
		 
		 ProductPromotionInstance.setActive("1");
		 ProductPromotionInstance.setDefaultDiscount("10.35");
		 ProductPromotionInstance.setProductDiscount("6.35");
		 ProductPromotionInstance.setProductId("d797f620-c552-11e7-9614-35f7986cc263");
		 
		 ProductPromotionListInstance.add(ProductPromotionInstance);
		 
		 
		 return ProductPromotionListInstance;
		 
	}
	
	public static List<ProductPromotion> getNullListPromotionProducts() {
		 
		 return null;
		 
	}
	
	
	public static List<CategoryPromotion> getListPromotionCategory() {
		 List<CategoryPromotion> categoryPromotionListInstance = new ArrayList<CategoryPromotion>();
		 CategoryPromotionDTO categoryPromotionInstance=new CategoryPromotionDTO();
		 
		 categoryPromotionInstance.setCategoryName("women-activewear");
		 categoryPromotionInstance.setDefaultDiscount("5.0");
		 
		 categoryPromotionListInstance.add(categoryPromotionInstance);
		 
		 return categoryPromotionListInstance;
		 
	}
	
	public static List<ProductCategoryPromotion> getListPromotionCategoriesProducts() {
		 List<ProductCategoryPromotion> ProductPromotionListInstance = new ArrayList<ProductCategoryPromotion>();
		 ProductCategoryPromotionDTO ProductCategoryPromotionInstance=new ProductCategoryPromotionDTO();
		 
		 ProductCategoryPromotionInstance.setActive("1");
		 ProductCategoryPromotionInstance.setDefaultDiscount("10.35");
		 ProductCategoryPromotionInstance.setProductDiscount("6.35");
		 ProductCategoryPromotionInstance.setProductId("d797f620-c552-11e7-9614-35f7986cc263");
		 
		 ProductPromotionListInstance.add(ProductCategoryPromotionInstance);
		 
		 
		 return ProductPromotionListInstance;
		 
	}
	
}
