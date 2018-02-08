/**
 * 
 */
package com.nisum.user.service.impl;

import com.nisum.user.dto.StoreDTO;
import com.nisum.user.model.Store;
import com.nisum.user.model.User;
import com.nisum.user.repository.StoreRepository;
import com.nisum.user.service.StoreService;
import com.nisum.user.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * The StoreService which uses storeRepository and userService to serve web methods
 * @author aali
 *
 */
@Service("storeService")
public class StoreServiceImpl implements StoreService {
	
	
	@Autowired
	private StoreRepository storeRepository;

	
	@Autowired
	private UserService userService;
	
	@Autowired
    private ModelMapper modelMapper;
	
	/**
	 * invokes storeRepository method finById
	 * @param storeId the storeId
	 * @return the Store
	 */
	public StoreDTO getStoreById(Long storeId) {
		Store store = storeRepository.findById(storeId);
		if(Objects.isNull(store)) throw new EmptyResultDataAccessException(1);
		return convertToDto(store);
	}
	
	/**
	 * invokes storeRepository method findByZipcode
	 * @param zipcode the zipcode
	 * @param pageable the pageable
	 * @return Stores
	 */
	@Transactional(readOnly=true)
	public Page<StoreDTO> getStoreByZipcode(Long zipcode, Pageable pageable) {
		
		Page<Store> stores = storeRepository.findByZipcode(zipcode,pageable);
		if (null != stores.getContent() && stores.getContent().size() > 0)
			return new PageImpl<>(stores.getContent().parallelStream()
					.map(store -> convertToDto(store)).collect(Collectors.toList()));
		throw new EmptyResultDataAccessException(1);
	}
	
	/**
	 * invokes storeRepository method save
	 * @param userId the userId
	 * @param storeId the storeId
	 * @return the Store
	 */
	@Transactional
	public StoreDTO saveStore(Long userId, Long storeId) {
		User currentUser = userService.findOne(userId);
		if(Objects.isNull(currentUser)) throw new EmptyResultDataAccessException("No user found", 1);
		Store selectStore = storeRepository.findOne(storeId);
		if(Objects.isNull(selectStore)) throw new EmptyResultDataAccessException("No store found", 1);
		selectStore.addUser(currentUser);
		StoreDTO dto = convertToDto(storeRepository.save(selectStore));
		if (null != dto) return dto;
		throw new DataIntegrityViolationException("Store not saved");
	}
	
	/**
	 * invokes storeRepository method findByUserId
	 * @param userId the userId
	 * @return the Store
	 */
	@Transactional(readOnly=true)
	public StoreDTO getStoreByUserId(Long userId) {
		Store currentStore = storeRepository.findByUserId(userId);
		if(Objects.isNull(currentStore)) throw new EmptyResultDataAccessException(1);
		return  convertToDto(currentStore);
		
	}
	@Transactional(readOnly=true)
	public Page<StoreDTO> getStores() {
		
		List<Store> stores = storeRepository.findAll();
		if(Objects.isNull(stores)) throw new EmptyResultDataAccessException(1);
		return new PageImpl<>(stores.parallelStream()
				.map(this::convertToDto).collect(Collectors.toList()));
	}
	
	
	private StoreDTO convertToDto(Store store) {
		return modelMapper.map(store, StoreDTO.class);
	}
	


}
