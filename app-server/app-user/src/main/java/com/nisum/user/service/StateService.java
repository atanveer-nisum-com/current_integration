package com.nisum.user.service;

import com.nisum.user.model.State;

import java.io.IOException;
import java.util.List;


/**
 * The Interface StateService.
 */
public interface StateService {
	
	/**
	 * Gets the states.
	 *
	 * @param countryId the country id
	 * @return the states
	 */
	public List<State> getStates(Long countryId);
	
	/**
	 * Gets the state by id.
	 *
	 * @param id the id
	 * @return the state by id
	 */
	public State findStateById(Long id);
	
	/**
	 * Gets the state by name.
	 *
	 * @param name the name
	 * @return the state by name
	 */
	public List<State> getStateByName(String name);
	
	

	/**
	 * Gets the state by country id or name.
	 *
	 * @param countryId the country id
	 * @param id the id
	 * @param name the name
	 * @return the state by country id or name
	 */
	public Object getStateByCountryIdOrName(Long countryId, Long id, String name);

	
	public String validateZipCode(String zipcode, String city, String state, String country) throws IOException;
	
}
