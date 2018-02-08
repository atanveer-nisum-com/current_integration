package com.nisum.shop.service;

import com.nisum.shop.api.dto.ProductDTO;

public interface ProductService {

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the productDTO
	 */
	public ProductDTO findOne(String id);
	
	/**
	 * Find available product for purchase.
	 *
	 * @param id the id
	 * @return the productDTO
	 */
	public ProductDTO findAvailable(String id);
}
