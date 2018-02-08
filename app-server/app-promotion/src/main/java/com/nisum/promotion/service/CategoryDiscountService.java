/**
 * 
 */
package com.nisum.promotion.service;

import com.nisum.promotion.dto.CategoryPromotion;

import java.util.List;
import java.util.Set;

/**
 * @author Kahmed
 *
 */
public interface CategoryDiscountService {

	/**
	 * 
	 * @param categoryIds
	 * @return
	 */
	public List<CategoryPromotion> getDiscountByCategories(Set<String> categoryIds);
}
