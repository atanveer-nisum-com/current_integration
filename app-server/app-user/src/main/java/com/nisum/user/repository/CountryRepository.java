package com.nisum.user.repository;

import com.nisum.user.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * The Interface CountryRepository.
 */
public interface CountryRepository extends JpaRepository<Country, Long> {

	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Country> findAll();
	
	/**
	 * Find by name containing.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<Country> findByNameContaining(String name);

}
