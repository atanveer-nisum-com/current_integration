package com.nisum.user.test.mock.models;

import com.nisum.user.dto.*;
import com.nisum.user.model.*;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public final class MockModelFactory {
	
	private static final ModelMapper mapper = new ModelMapper();
	
	public static List<Wishlist> getMockPageWishlist() {
		List<Wishlist> wishlists = new ArrayList<>();
		for (int i = 0; i < 3; ++i) wishlists.add(getMockWishlist());
		
		return wishlists;
	}
	
	public static List<WishlistResponseDTO> getMockWishlistResponseDTOs() {
		return getMockPageWishlist().parallelStream().map(wishlist -> mapper.map(wishlist, WishlistResponseDTO.class))
																	.collect(Collectors.toList());
	}
	
	public static Wishlist getMockWishlist() {
		Wishlist wishlist = new Wishlist();
		wishlist.setId(1L);
		wishlist.setName("Mock Wishlist");
		wishlist.setWishlistItems(new HashSet<WishlistItem>());
		
		return wishlist;
	}
	
	public static WishlistDTO getMockWishlistDTO() {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setWishlistId(1L);
		wishlistDTO.setName("Mock Wishlist");
		wishlistDTO.setIsDefault(true);
		return wishlistDTO;
	}
	
	public static WishlistDTO getNullMockWishlistDTO() {
		WishlistDTO wishlistDTO = new WishlistDTO();
		wishlistDTO.setWishlistId(null);
		wishlistDTO.setName(null);
		wishlistDTO.setIsDefault(false);
		return wishlistDTO;
	}
	
	public static WishlistItem getMockWishlistItems() {
		
		WishlistItem wishListItems = new WishlistItem();
		
		wishListItems.setId(1l);
		wishListItems.setIsDeleted(false);
		wishListItems.setItemUUID("e82ef150-c9df-11e7-b85a-a7635ce640a7");
		wishListItems.setWishlist(new Wishlist());
		
		
		return wishListItems;
	}
	
	public static User getMockUser() {
		
		User user = new User();
		user.setId(2L);
		user.setFirstName("mtb");
		user.setAddresses(new HashSet<Address>());
		user.setEmailAddress("mtb@nisum.com");
		user.setPassword("mtb");
		user.setResetToken("tokenValue");
		
		user.setUserPreferences(new HashSet<UserPreference>());
		user.setStore(new Store());
		
		user.setWishlists(new HashSet<Wishlist>());

		return user;
	}
	
	public static ProfileDTO getMockProfileDTO() {
		ProfileDTO profileDTO = new ProfileDTO();
		
		profileDTO.setFirstName("mtb");
		profileDTO.setEmailAddress("mtb@nisum.com");
		profileDTO.setCurrentPassword("123");
		profileDTO.setNewPassword("hello");
		profileDTO.setConfirmPassword("hello");
		profileDTO.setAddresses(new HashSet<AddressDTO>());
		return profileDTO;
	}

	public static State getMockState() {
		
		State state = new State();
		
		state.setId(1L);
		state.setAbbreviation("AK");
		state.setName("Alaska");
		state.setCountry(getMockCountry());
		
		return state;
	}
	
	/**
	 * Creates the mock address.
	 *
	 * @return the address
	 */
	public static Address getMockAddress() {
		
		Address address = new Address();
		Country country = getMockCountry();
		State state = getMockState();
		User user = getMockUser();
		
		
		
		
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
	public static AddressDTO getMockAddressDTO() {
		
		AddressDTO address = new AddressDTO();
		
		address.setAddressLine1("Address Line1 ");
		address.setAddressLine2("Address Line 2");
		address.setAddressType((byte)1);
		address.setCity("NY City");
		address.setCountryBean(getMockCountryBean());
		//address.setId(1L);
		address.setIsDefault(1);
		address.setPhoneNumber("4209211");
		address.setStateBean(getMockStateBean());
		address.setZipcode("74700");
		
		
		
		return address;
		
		
	}
	
	/**
	 * Gets the mock state bean.
	 *
	 * @return the mock state bean
	 */
	public static StateBeanDTO getMockStateBean() {
		
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
	public static CountryBeanDTO getMockCountryBean() {
		
		CountryBeanDTO country = new CountryBeanDTO();
		country.setId(1);
		country.setName("USA");
		
		return country;
		
	}
	
	public static Country getMockCountry() {
		
		Country country = new Country();
		country.setAbbreviation("US");
		country.setId(230L);
		country.setName("USA");
		
		return country;
		
	}
	
	

}
