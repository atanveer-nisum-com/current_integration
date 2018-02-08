package com.nisum.shop.service;

import com.nisum.common.shop.dto.RegistrationDTO;
import com.nisum.common.shop.dto.UserDTO;

public interface UserService {

	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the user
	 */
	public UserDTO findOne(Long id);
	
	/**
	 * Checks if is alive.
	 *
	 * @param userId the user id
	 * @param accessToken the access token
	 * @return the boolean
	 */
	public Boolean isAlive(String userId, String accessToken);
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public Boolean update(RegistrationDTO registrationDTO);
	
	/**
	 * Save guest.
	 *
	 * @return the user
	 */
	public UserDTO saveGuest();
}
