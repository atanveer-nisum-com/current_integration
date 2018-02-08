package com.nisum.user.test.service;

import com.nisum.user.model.Address;
import com.nisum.user.model.Country;
import com.nisum.user.model.User;
import com.nisum.user.repository.AddressRepository;
import com.nisum.user.service.AddressService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;


// TODO: Auto-generated Javadoc
/**
 * The Class AddressServiceTest.
 */

public class AddressServiceTest extends BaseServiceTest {

	/* The address service. */
	@Autowired 
	private AddressService addressService;
	
	/** The address repository. */
	@Autowired
	private AddressRepository addressRepository;
	
	/**
	 * Should retrieve address against id.
	 */
	@Test
	public void shouldSaveAddress() {
		Address persistedAddress;
		Address mockAddress = createMockAddress();
		
		Mockito.when(addressRepository.save(any(Address.class))).thenReturn(mockAddress);
		
		persistedAddress = addressService.save(mockAddress);
		
		assert(persistedAddress.getCity().equals("NY City"));
		
	}
	
	/**
	 * Should retrieve address against id.
	 */
	@Test
	public void shouldRetrieveAddressAgainstId() {
		
		Address persistedAddress;
		Address mockAddress = createMockAddress();
		
		Mockito.when(addressRepository.findAddressById(anyLong())).thenReturn(mockAddress);
		
		persistedAddress = addressService.findOne(1L);
		
		assert(persistedAddress.getCity().equals("NY City"));
	}
	
	/**
	 * Should retrieve address against user id.
	 */
	@Test
	public void shouldRetrieveAddressAgainstUserId() {
		
		Address persistedAddress;
		Address mockAddress = createMockAddress();
		
		Mockito.when(addressRepository.findAddressByUserId(anyLong())).thenReturn(mockAddress);
		
		
		persistedAddress = addressService.findAddressByUserId(1L);
		
		
		assert(persistedAddress.getCity().equals("NY City"));
	}

	
	/**
	 * Creates the mock address.
	 *
	 * @return the address
	 */
	private Address createMockAddress() {
		
		Address address = new Address();
		Country country = new Country();
		User user = new User();
		user.setId(1L);
		user.setFirstName("John");
		
		
		country.setAbbreviation("US");
		country.setId(1L);
		country.setName("USA");
		
		address.setAddressLine1("Address Line 1");
		address.setId(1L);
		address.setAddressLine2("Address Line 2");
		address.setCity("NY City");
		address.setAddressType((byte)0);
		address.setCountryBean(country);
		address.setIsDefault((byte)1);
		address.setIsDeleted((byte)0);
		address.setUser(user);
		address.setId(1L);
		
		
		return address;
	}
}