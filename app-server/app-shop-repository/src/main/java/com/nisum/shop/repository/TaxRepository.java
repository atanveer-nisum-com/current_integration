package com.nisum.shop.repository;

import com.nisum.shop.model.Tax;
import org.springframework.data.jpa.repository.JpaRepository;


/**
 * The Interface TaxRepository.
 */
public interface TaxRepository  extends JpaRepository<Tax, Long>{

	/**
	 * Find by abbrv.
	 *
	 * @param abbrv the abbrv
	 * @return the tax
	 */
	public Tax findByAbbrv(String abbrv);

	/**
	 * Find by state.
	 *
	 * @param state the state
	 * @return the tax
	 */
	public Tax findByState(String state);

}
