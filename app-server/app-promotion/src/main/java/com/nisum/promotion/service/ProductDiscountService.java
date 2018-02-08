/**
 * 
 */
package com.nisum.promotion.service;

import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;

import java.util.List;

/**
 * @author Nisum Pakistan
 *
 * The Interface ProductDiscountService
 */
public interface ProductDiscountService {

	/**
	 * Get Discount by ProductIds
	 * @param productIds
	 * @return List<Object>
	 */
	public List<ProductPromotion> getDiscountByProducts(String [] productIds);
	
	public List<ProductCategoryPromotion> getDiscountByProductAndCategories(String[] productIds);
}
