/**
 * 
 */
package com.nisum.shop.service;

import com.nisum.shop.model.Tax;

import java.util.List;


/**
 * The Interface TaxService.
 *
 * @author mabdullah
 */
public interface TaxService {
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the tax
	 */
	public Tax findOne(Long id);
	
	/**
	 * Find by abbrv.
	 *
	 * @param name the name
	 * @return the tax
	 */
	public Tax findByAbbrv(String name);

	/**
	 * Find by attributes.
	 *
	 * @param abbr the abbr
	 * @param id the id
	 * @param state the state
	 * @return the tax
	 */
	public Tax findByAttributes(String abbr, Long id, String state);
	
	/**
	 * Find by state.
	 *
	 * @param state the state
	 * @return the tax
	 */
	public Tax findByState(String state);

	/**
	 * Find all.
	 *
	 * @return the list
	 */
	public List<Tax> findAll();
}
