package com.nisum.shop.service;

import com.nisum.common.shop.dto.RegistrationDTO;

public interface UserCommandService {
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public String update(RegistrationDTO registrationDTO);
}
