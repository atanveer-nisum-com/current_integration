package com.nisum.user.repository;


import com.nisum.user.model.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The StoreRepository
 * @author aali
 *
 */
@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
	
	/**
	 * find the store by corresponding storeid
	 * @param storeId the storeid
	 * @return Store
	 */
	public Store findById(Long storeId);
			
	/**
	 * find all stores corresponding to zipcode
	 * @param zipcode the zipcode
	 * @param pageable the pageable
	 * @return the list of stores
	 */
	public Page<Store> findByZipcode(Long zipcode,Pageable pageable);

	@Query("SELECT s from User u JOIN u.store s WHERE u.id = ?1")
	public Store findByUserId(Long userId);
	
	public List<Store> findAll();

}
