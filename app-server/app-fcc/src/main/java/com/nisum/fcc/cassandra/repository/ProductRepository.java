/**
 * 
 */
package com.nisum.fcc.cassandra.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.nisum.fcc.cassandra.model.Product;

/**
 * @author Nisum Pakistan
 *
 */
public interface ProductRepository extends CrudRepository<Product, String> {

	@Query(value="SELECT * from product where row_counter >= ?0 AND row_counter <= ?1 ALLOW FILTERING")
	List<Product> findWithLimit(int size, int page) throws Exception;
	
	@Query(value="SELECT * from product_by_category where category = ?0 limit 100")
	List<Product> findByCategoryName(String categoryName);

	@Query(value="SELECT * from product_by_category where category = ?0 limit 100")
	List<Product> getProductByCategory(Object categoryId, Pageable pageable);
	
	@Query(value="SELECT * FROM product WHERE code =?0 AND name =?1")
	Product getProductByCodeAndName(String code, String name);

	@Query(value="SELECT * from product_by_id where token(id) = token(?0)")
	Product findById(UUID itemId);
	
	@Query(value="SELECT * FROM product WHERE buyable = 1 AND displayable =1 AND quantity> 0 AND is_deleted = 0 AND id = ?0 ALLOW FILTERING;")
	Product findActiveProductForCartById(UUID itemId);
	
	List<Product> findBySearchStrContaining(String searchStr);
}
