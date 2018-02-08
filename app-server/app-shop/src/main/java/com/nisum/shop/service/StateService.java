package com.nisum.shop.service;

import com.nisum.shop.model.State;

public interface StateService {
	
	/**
	 * Gets the state by id.
	 *
	 * @param id the id
	 * @return the state by id
	 */
	public State findStateById(Long id);

}
