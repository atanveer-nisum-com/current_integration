/**
 * 
 */
package com.nisum.promotion.service.impl;

import com.nisum.promotion.dto.CategoryPromotion;
import com.nisum.promotion.repository.CategoryDiscountRepository;
import com.nisum.promotion.service.CategoryDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * @author Kahmed
 *
 */
@Service("categorydiscountservice")
public class CategoryDiscountServiceImpl implements CategoryDiscountService {
	
	@Autowired
	private CategoryDiscountRepository categoryDiscountRepository;

	@Transactional(readOnly = true)
	public List<CategoryPromotion> getDiscountByCategories(Set<String> categoryIds) {
		List<CategoryPromotion> categoryPromotion = categoryDiscountRepository.getDiscountByCategories(categoryIds);
		if (categoryPromotion == null || categoryPromotion.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		return categoryPromotion;
	}

}
