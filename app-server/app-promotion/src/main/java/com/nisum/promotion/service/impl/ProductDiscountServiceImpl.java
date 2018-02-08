/**
 * 
 */
package com.nisum.promotion.service.impl;

import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;
import com.nisum.promotion.repository.ProductDiscountRepository;
import com.nisum.promotion.service.ProductDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @author Nisum Pakistan
 *
 * The implementation class of {@link ProductDiscountService}
 */
@Service("productDiscountService")
public class ProductDiscountServiceImpl implements ProductDiscountService {

	@Autowired
	private ProductDiscountRepository productDiscountRepository;
	/* (non-Javadoc)
	 * @see com.nisum.service.ProductDiscountService#getDiscountByProducts(java.util.List)
	 */
	
	@Transactional(readOnly = true)
	public List<ProductPromotion> getDiscountByProducts(String [] productIds) {
		List<ProductPromotion> productPromotion = productDiscountRepository.findDiscountByProductIds(productIds);
		
		if (productPromotion == null || productPromotion.size() == 0) {
			throw new EmptyResultDataAccessException(1);
		}
		
		return productPromotion;
	}

	@Transactional(readOnly = true)
	public List<ProductCategoryPromotion> getDiscountByProductAndCategories(String[] productIds) {
		List<ProductCategoryPromotion> productCategoryPromotion = productDiscountRepository.findDiscountByProductAndCategoryIds(productIds);
		if (productCategoryPromotion == null || productCategoryPromotion.size() < 1)
			throw new EmptyResultDataAccessException(1);
		return productCategoryPromotion;
	}
}
