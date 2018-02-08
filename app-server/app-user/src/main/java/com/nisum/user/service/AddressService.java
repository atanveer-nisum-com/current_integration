package com.nisum.user.service;

import com.nisum.user.model.Address;


/**
 * The Interface AddressService.
 */
public interface AddressService {
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the address
	 */
	public Address findOne(Long id);
	
	/**
	 * Find address by user id.
	 *
	 * @param userId the user id
	 * @return the address
	 */
	public Address findAddressByUserId(Long userId);
	
	/**
	 * Save.
	 *
	 * @param address the address
	 * @return the address
	 */
	public Address save(Address address);
}
