package com.nisum.user.service.impl;

import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.AddressDTO;
import com.nisum.user.model.Address;
import com.nisum.user.model.User;
import com.nisum.user.repository.AddressRepository;
import com.nisum.user.repository.CountryRepository;
import com.nisum.user.repository.StateRepository;
import com.nisum.user.repository.UserRepository;
import com.nisum.user.service.AddressBookService;
import com.nisum.user.util.AddressConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

/**
 * The Class AddressBookServiceImpl.
 */
@Service
public class AddressBookServiceImpl implements AddressBookService{

	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private StateRepository stateRepository;
	
	@Autowired
	AddressConverter converter;
	
	/* (non-Javadoc)
	 * @see com.nisum.service.AddressBookService#save(com.nisum.dto.AddressDTO)
	 */
	@Transactional
	@Override
	public Page<AddressDTO> save(AddressDTO addressDto, long userId) {
		Address address = null;
		
		if(!Objects.isNull(addressDto)){
			if(!Objects.isNull(addressDto.getId())){
				address = addressRepository.findOne(addressDto.getId());
				throw new DuplicateKeyException("Address already exists");
			}
			Page<Address> addresses = addressRepository.findAddressesByUserId(userId,null);
			if(addresses.getTotalElements() >= 10){
				throw new IncorrectResultSizeDataAccessException("Address limit reached", 9,
						Math.toIntExact(addresses.getTotalElements()));
			}
			if(addressDto.getIsDefault() == 1){
				Address defaultAddress = addressRepository.findDefaultAddressByUserId(userId);
				if(!Objects.isNull(defaultAddress)){
					defaultAddress.setIsDefault((byte)0);
					this.update(converter.convertToDto(defaultAddress),userId);
				}
//				address = this.populateDomainBean(addressDto,userId);
				address = convertToEntity(addressDto,userId);
				addressRepository.saveAndFlush(address);
			} else {
//				address = this.populateDomainBean(addressDto,userId);
				address = convertToEntity(addressDto,userId);
				addressRepository.saveAndFlush(address);
			}
		}
		
		Page<AddressDTO> pageToReturn = converter.convertEntitiesToDtos(addressRepository.findAddressesByUserId(userId, null));
		return pageToReturn;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.AddressBookService#findAddressListByUserId(java.lang.Long, org.springframework.data.domain.Pageable)
	 */
	@Transactional
	@Override
	public Page<AddressDTO> findAddressListByUserId(Long userId,Pageable pageable) {

		Page<AddressDTO> addresses = converter.convertEntitiesToDtos(addressRepository.findAddressesByUserId(userId, pageable));
		if(Objects.isNull(addresses)) throw new EmptyResultDataAccessException(1);
		return addresses;
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.AddressBookService#populateDomainBean(com.nisum.dto.AddressDTO)
	 */
/*	@Override
	public Address populateDomainBean(AddressDTO source, long userId){
		
		User user = userRepository.findUserById(userId);
		//User user = new User();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		Address address = objectMapper.convertValue(source, Address.class);
		address.setCountryBean(countryRepository.findOne(address.getCountryBean().getId()));
		if(source.getStateBean()!=null)
			address.setStateBean(stateRepository.findOne((long)source.getStateBean().getId()));
		address.setIsDeleted((byte)0);
		address.setUser(user);
		address.setCreatedAt(DateUtils.getCurrentTime());
		return address;
	}*/
	private Address convertToEntity(AddressDTO address, long userId) {
		Address convertedAddress = converter.convertToEntity(address);
		User user = userRepository.findUserById(userId);
		convertedAddress.setIsDeleted((byte)0);
		convertedAddress.setUser(user);
		convertedAddress.setCreatedAt(DateUtils.getCurrentTime());
		return convertedAddress;
	}

	/* (non-Javadoc)
	 * @see com.nisum.service.AddressBookService#update(com.nisum.model.Address)
	 */
	@Override
	public Page<AddressDTO> update(AddressDTO address, long userId) {
//		Address convertedAddress = this.populateDomainBean(address, userId);
		Address convertedAddress = convertToEntity(address, userId);

		convertedAddress.setUpdatedAt(System.currentTimeMillis() / 1000L);
		if(convertedAddress.getIsDeleted()== null){
			convertedAddress.setIsDeleted((byte) 0);
		}
		if(address.getIsDefault() == 1){
			Address defaultAddress = addressRepository.findDefaultAddressByUserId(userId);
			if(!Objects.isNull(defaultAddress)){
				defaultAddress.setIsDefault((byte)0);
				
				AddressDTO defaultAddressDTO = converter.convertToDto(defaultAddress);
				this.update(defaultAddressDTO,userId);
			}
			convertedAddress = addressRepository.saveAndFlush(convertedAddress);
		}
		else{
			convertedAddress = addressRepository.saveAndFlush(convertedAddress);
		}

		if (Objects.isNull(convertedAddress)) throw new EmptyResultDataAccessException(1);
		return converter.convertEntitiesToDtos(addressRepository.findAddressesByUserId(userId, null));

	}


	/* (non-Javadoc)
	 * @see com.nisum.service.AddressBookService#removeAddress(java.lang.Long)
	 */
	@Transactional
	public Page<AddressDTO> removeAddress(Long addressId,long userId) {
		Address address = addressRepository.findOne(addressId);
		if(Objects.isNull(address)) {
			throw new EmptyResultDataAccessException(1);
		} else {
			addressRepository.deleteAddress(addressId);
			return converter.convertEntitiesToDtos(addressRepository.findAddressesByUserId(userId, null));
		}
		
	}
	
}
