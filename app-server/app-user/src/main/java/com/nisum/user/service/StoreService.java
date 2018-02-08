package com.nisum.user.service;


import com.nisum.user.dto.StoreDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * The Interface StoreService
 * @author aali
 *
 */
public interface StoreService {
	
	public StoreDTO getStoreById(Long storeId);
	
	/**
	 * get stores by zipcode
	 * @param zipcode the zipcode
	 * @param pageable the pageable
	 * @return Stores 
	 */
	public Page<StoreDTO> getStoreByZipcode(Long zipcode, Pageable pageable);
	
	/**
	 * saves stores for that user
	 * @param userId the userid
	 * @param storeId the storeid
	 */
	public StoreDTO saveStore(Long userId, Long storeId);
	
	/**
	 * get store by userid
	 * @param userId the userid
	 * @return
	 */
	public StoreDTO getStoreByUserId(Long userId);
	/**
	 * get all stores
	 * @return the pageable with stores
	 */
	public Page<StoreDTO> getStores();
}
