/**
 * 
 */
package com.nisum.promotion.repository;

import com.nisum.promotion.dto.ProductCategoryPromotion;
import com.nisum.promotion.dto.ProductPromotion;
import com.nisum.promotion.model.PromotionDiscount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


/**
 * @author Nisum Pakistan
 * The Interface ProductDiscountRepository
 */
public interface ProductDiscountRepository extends JpaRepository<PromotionDiscount, Long>{

	/**
	 * find the discounts by productIds
	 * @param productIds
	 * @return List<Objects>
	 */
	
	@Query(value="SELECT "
			+ "CAST(pd.active as Char(1)) as active,pd.discount_value as defaultDiscount,ppi.discount_value as productDiscount,ppi.product_id as productId FROM promotion_discount"
			+ " pd LEFT JOIN product_promotion_link ppi ON pd.id = ppi.promotion_id where ppi.product_id IN ?1 AND pd.active=1", nativeQuery = true )
	public List<ProductPromotion> findDiscountByProductIds(String [] productIds);
	
	
	@Query(value="SELECT "
			+ "CAST(pd.active as Char(1)) as active, pd.category_name AS categoryName, pd.discount_value as defaultDiscount,ppi.discount_value as productDiscount,ppi.product_id as productId,pt.promotion_name as promotionType FROM promotion_discount"
			+ " pd  INNER JOIN" 
			+ " ecommerce_promotions.promotion_type pt ON pd.promotion_type_id = pt.id LEFT JOIN product_promotion_link ppi ON pd.id = ppi.promotion_id AND ppi.product_id IN ?1 WHERE pd.active=1", nativeQuery = true)
	public List<ProductCategoryPromotion> findDiscountByProductAndCategoryIds(String[] productIds);
}
