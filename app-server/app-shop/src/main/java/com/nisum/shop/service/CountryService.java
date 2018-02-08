package com.nisum.shop.service;

import com.nisum.shop.model.Country;

public interface CountryService {
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the country
	 */
	public Country findById(Long id);

}
