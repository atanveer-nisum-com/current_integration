package com.nisum.user.repository;

import com.nisum.user.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The Interface AddressRepository.
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<Address> findAll();

	/**
	 * Find address by id.
	 *
	 * @param id
	 *            the id
	 * @return the address
	 */
	public Address findAddressById(Long id);

	/**
	 * Find address by user id.
	 *
	 * @param id
	 *            the id
	 * @return the address
	 */
	public Address findAddressByUserId(Long id);

	/**
	 * Find addresses by user id.
	 *
	 * @param id            the id
	 * @param pageable the pageable
	 * @return the addresses
	 */
	@Query("SELECT ad FROM Address ad WHERE ad.user.id = ?1 and ad.isDeleted = 0 ORDER BY ad.isDefault DESC")
	public Page<Address> findAddressesByUserId(Long id, Pageable pageable);

	/**
	 * Find default address by user id.
	 *
	 * @param id the id
	 * @return the address
	 */
	@Query("SELECT distinct ad FROM Address ad WHERE ad.user.id = ?1 and ad.isDefault = 1 and ad.isDeleted = 0")
	public Address findDefaultAddressByUserId(Long id);

	/**
	 * Delete address.
	 *
	 * @param addressId the address id
	 */
	@Modifying
	@Query("UPDATE Address ad SET ad.isDeleted = 1 WHERE ad.id = ?1")
	public void deleteAddress(Long addressId);
}
