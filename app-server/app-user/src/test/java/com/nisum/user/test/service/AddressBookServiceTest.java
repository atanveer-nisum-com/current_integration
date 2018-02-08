package com.nisum.user.test.service;

import com.nisum.user.dto.AddressDTO;
import com.nisum.user.dto.CountryBeanDTO;
import com.nisum.user.dto.StateBeanDTO;
import com.nisum.user.model.Address;
import com.nisum.user.model.Country;
import com.nisum.user.model.State;
import com.nisum.user.model.User;
import com.nisum.user.repository.AddressRepository;
import com.nisum.user.repository.CountryRepository;
import com.nisum.user.repository.StateRepository;
import com.nisum.user.repository.UserRepository;
import com.nisum.user.service.AddressBookService;
import com.nisum.user.service.CountryService;
import com.nisum.user.service.StateService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyLong;


// TODO: Auto-generated Javadoc
/**
 * The Class AddressBookServiceTest.
 */

public class AddressBookServiceTest extends BaseServiceTest {
	
	/** The user repository. */
	@Autowired
	private UserRepository userRepository;
	
	/** The country repository. */
	@Autowired
	private CountryRepository countryRepository;
	
	/** The state repository. */
	@Autowired
	private StateRepository stateRepository;
	
	/** The address book service. */
	@Autowired
	private AddressBookService addressBookService;

	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	/**
	 * Should save user address and return.
	 */
	//public Page<AddressDTO> save(AddressDTO addressDto, long userId)
	@Test
	public void shouldSaveUserAddressAndReturn() {
		
		AddressDTO addressDTO = this.getMockAddressDTO();
		Address address = this.getMockAddress();
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		
		
		Page<Address> mockAddressPage = new PageImpl<Address>(addressList);
		
		
		Page<AddressDTO> persistedAddressPage;
		
		
		//Mock all repositories methods 
		Mockito.when(addressRepository.findOne(anyLong())).thenReturn(address);
		Mockito.when(addressRepository.findAddressesByUserId(anyLong(),any())).thenReturn(mockAddressPage);
		Mockito.when(addressRepository.findDefaultAddressByUserId(anyLong())).thenReturn(address);
		
		
		Mockito.when(userRepository.findUserById(anyLong())).thenReturn(this.getMockUser());
		Mockito.when(countryRepository.findOne(anyLong())).thenReturn(this.getMockCountry());
		Mockito.when(stateRepository.findOne(anyLong())).thenReturn(this.getMockState());
		
		Mockito.when(addressRepository.saveAndFlush(address)).thenReturn(address);
		
		
		
		persistedAddressPage = addressBookService.save(addressDTO, 1);
		
		assertTrue(persistedAddressPage.getContent().get(0).getCity().equals("NY City"));
		
		
	}
	
	
	
	/**
	 * Should find address list by user id.
	 */
	@Test
	public void shouldFindAddressListByUserId() {
		
		
		Address address = this.getMockAddress();
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		
		
		Page<Address> mockAddressPage = new PageImpl<Address>(addressList);
		
		
		Page<AddressDTO> persistedAddressPage;
		
		Mockito.when(addressRepository.findAddressesByUserId(anyLong(),any())).thenReturn(mockAddressPage);
		
		
		persistedAddressPage = addressBookService.findAddressListByUserId(1L, null);
		
		assertTrue(persistedAddressPage.getContent().get(0).getCity().equals("NY City"));
		
		
	}
	
	
	/**
	 * Should update address book.
	 */
	@Test
	public void shouldUpdateAddressBook() {
		
		AddressDTO addressDTO = this.getMockAddressDTO();
		Address address = this.getMockAddress();
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		Page<Address> mockAddressPage = new PageImpl<Address>(addressList);
		Page<AddressDTO> persistedAddressPage;
		
		
		Mockito.when(addressRepository.findAddressesByUserId(anyLong(),any())).thenReturn(mockAddressPage);
		Mockito.when(addressRepository.findDefaultAddressByUserId(anyLong())).thenReturn(address);
		Mockito.when(addressRepository.saveAndFlush(address)).thenReturn(address);
		
		
		
		persistedAddressPage = addressBookService.update(addressDTO, 1L);
		
		assertTrue(persistedAddressPage.getContent().get(0).getCity().equals("NY City"));
		
	}
	
	/**
	 * Should remove address.
	 */
	@Test
	
	public void shouldRemoveAddress() {
		
		Address address = this.getMockAddress();
		List<Address> addressList = new ArrayList<Address>();
		addressList.add(address);
		Page<Address> mockAddressPage = new PageImpl<Address>(addressList);
		Page<AddressDTO> persistedAddressPage;
		
		
		Mockito.when(addressRepository.findOne(anyLong())).thenReturn(address);
		Mockito.when(addressRepository.findAddressesByUserId(anyLong(),any())).thenReturn(mockAddressPage);
		Mockito.doNothing().when(addressRepository).deleteAddress(anyLong());
		
		
		
		persistedAddressPage = addressBookService.removeAddress(1L, 1);		
		assertTrue(persistedAddressPage==null || persistedAddressPage.getSize()<=0);
	}
	
	
	
	
	
	/**
	 * Gets the mock user.
	 *
	 * @return the mock user
	 */
	private User getMockUser() {
		
		User user = new User();
		user.setId(1L);
		user.setFirstName("John");
		return user;
	}
	
	/**
	 * Gets the mock country.
	 *
	 * @return the mock country
	 */
	private Country getMockCountry() {
		
		Country country = new Country();
		country.setAbbreviation("US");
		country.setId(1L);
		country.setName("USA");
		
		return country;
		
	}
	
	/**
	 * Gets the mock state.
	 *
	 * @return the mock state
	 */
	private State getMockState() {
		
		State state = new State();
		
		state.setId(1L);
		state.setAbbreviation("NY");
		state.setName("New York");
		state.setCountry(this.getMockCountry());
		
		return state;
	}
	
	/**
	 * Creates the mock address.
	 *
	 * @return the address
	 */
	private Address getMockAddress() {
		
		Address address = new Address();
		Country country = this.getMockCountry();
		State state = this.getMockState();
		User user = this.getMockUser();
		
		
		
		
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
		address.setStateBean(state);
		
		
		return address;
	}

	/**
	 * Gets the mock address DTO.
	 *
	 * @return the mock address DTO
	 */
	private AddressDTO getMockAddressDTO() {
		
		AddressDTO address = new AddressDTO();
		
		address.setAddressLine1("Address Line1 ");
		address.setAddressLine2("Address Line 2");
		address.setAddressType((byte)1);
		address.setCity("NY City");
		address.setCountryBean(this.getMockCountryBean());
		//address.setId(1L);
		address.setIsDefault(1);
		address.setPhoneNumber("4209211");
		address.setStateBean(this.getMockStateBean());
		address.setZipcode("74700");
		
		
		
		return address;
		
		
	}
	
	/**
	 * Gets the mock state bean.
	 *
	 * @return the mock state bean
	 */
	private StateBeanDTO getMockStateBean() {
		
		StateBeanDTO state = new StateBeanDTO();
		
		state.setId(1);
		state.setName("NY");
		
		return state;
	}
	
	/**
	 * Gets the mock country bean.
	 *
	 * @return the mock country bean
	 */
	private CountryBeanDTO getMockCountryBean() {
		
		CountryBeanDTO country = new CountryBeanDTO();
		country.setId(1);
		country.setName("USA");
		
		return country;
		
	}
}
