package com.nisum.user.service;


import com.nisum.user.dto.AddressDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Interface AddressBookService.
 */
public interface AddressBookService {
	
	/**
	 * Find address by user id.
	 *
	 * @param userId the user id
	 * @param pagable the pagable
	 * @return the address
	 */
	public Page<AddressDTO> findAddressListByUserId(Long userId, Pageable pagable);
	
	/**
	 * Save.
	 *
	 * @param addressDto the address dto
	 * @return the page
	 */
	public Page<AddressDTO> save(AddressDTO addressDto, long userId);
	
	/**
	 * Update.
	 *
	 * @param address the address
	 * @return the page
	 */
	public Page<AddressDTO> update(AddressDTO address, long userId);
	
	/**
	 * Populate domain bean.
	 *
	 * @param source the source
	 * @return the address
	 */
//	public Address populateDomainBean(AddressDTO source,long userId);
	
	/**
	 * Removes the address.
	 *
	 * @param addressId the address id
	 * @return the page
	 */
	public Page<AddressDTO> removeAddress(Long addressId,long userId);
	
}
