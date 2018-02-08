package com.nisum.user.service.impl;

import com.nisum.user.model.Address;
import com.nisum.user.repository.AddressRepository;
import com.nisum.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("addressService")
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressRepository addressRepository;
	
	public Address findOne(Long id) {
		return addressRepository.findAddressById(id);
	}
	
	public Address save(Address address) {
		return addressRepository.save(address);
	}

	public Address findAddressByUserId(Long userId) {
		return addressRepository.findAddressByUserId(userId);
	}
}
