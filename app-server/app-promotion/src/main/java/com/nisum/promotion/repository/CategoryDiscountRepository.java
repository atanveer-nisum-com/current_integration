/**
 * 
 */
package com.nisum.promotion.repository;

import com.nisum.promotion.dto.CategoryPromotion;
import com.nisum.promotion.model.PromotionDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

/**
 * @author Nisum Pakistan
 *
 */
public interface CategoryDiscountRepository extends JpaRepository<PromotionDiscount, Long>{

	@Query(value="SELECT pd.category_name as categoryName, pd.discount_value as defaultDiscount FROM promotion_discount "
			+ "pd where pd.category_name IN ?1 AND pd.active=1",nativeQuery = true)
	public List<CategoryPromotion> getDiscountByCategories(Set<String> categoryIds);
	
	
}
