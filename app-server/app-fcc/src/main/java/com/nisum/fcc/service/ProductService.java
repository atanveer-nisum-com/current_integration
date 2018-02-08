package com.nisum.fcc.service;

import com.nisum.fcc.dto.ProductDTO;
import com.nisum.fcc.dto.ShopProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;



/**
 * The Interface ProductService.
 */
public interface ProductService {

	/**
	 * Gets the all by page.
	 *
	 * @param pageable the pageable
	 * @return the all by page
	 * @throws Exception 
	 */
	public List<ProductDTO> getAllByPage(Pageable pageable) throws Exception;
	
	
	
	/**
	 * Gets the product by category.
	 *
	 * @param categoryId the category id
	 * @param pageable the pageable
	 * @return the product by category
	 */
	public Page<ProductDTO> getProductByCategory(Object categoryId,Pageable pageable);
	
		
	/**
	 * Find by unique identifier.
	 *
	 * @param uniqueIdentifer the unique identifer
	 * @return the product DTO
	 */
	public ProductDTO findByUniqueIdentifier(String uniqueIdentifer);


	/**
	 * Gets the product by id.
	 *
	 * @param itemId the item id
	 * @return the product by id
	 */
	public ProductDTO getProductById(UUID itemId,Optional<Boolean> isForCart);
	
	/**
	 * Gets the product by id
	 * 
	 * @param itemId the item id
	 * @return the product by id
	 */
	public ShopProductDTO getProductById(UUID itemId);

	/**
	 * Search product by name
	 * 
	 * @param name the product name
	 * @return the List of product containing characters in input name
	 */
	public List<ProductDTO> searchProductByName(String name);
	
	

}
