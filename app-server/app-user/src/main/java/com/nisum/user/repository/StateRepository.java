package com.nisum.user.repository;

import com.nisum.user.model.State;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


/**
 * The Interface StateRepository.
 */
public interface StateRepository extends JpaRepository<State, Long> {

	/**
	 * Find by country id.
	 *
	 * @param countryId the country id
	 * @return the list
	 */
	public List<State> findByCountryId(Long countryId);
	
	/**
	 * Find by name containing.
	 *
	 * @param name the name
	 * @return the list
	 */
	public List<State> findByNameContaining(String name);

}
