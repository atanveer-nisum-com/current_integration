package com.nisum.user.service;

import com.nisum.user.model.Country;

import java.util.List;


// TODO: Auto-generated Javadoc
/**
 * The Interface CountryService.
 */
public interface CountryService {
	
	/**
	 * Gets the countries.
	 *
	 * @return the countries
	 */
	public List<Country> getCountries();
	
	/**
	 * Find by id.
	 *
	 * @param id the id
	 * @return the country
	 */
	public Country findById(Long id);
	
	/**
	 * Find by name.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<Country> findByName(String name);
	
	
	
	/**
	 * Gets the country by id or name.
	 *
	 * @param id the id
	 * @param name the name
	 * @return the country by id or name
	 */
	public Object getCountryByIdOrName(Long id, String name);
}
