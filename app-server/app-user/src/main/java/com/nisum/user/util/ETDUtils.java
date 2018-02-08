package com.nisum.user.util;

import com.nisum.user.dto.*;
import com.nisum.user.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The Class ETDUtils.
 */
public class ETDUtils {
	
	
	public static Page<AddressDTO> convertAddressesToAddressesDTOPage(Page<Address> addressEntity) {
		List<AddressDTO> addressesList = new ArrayList<>();
		
		for(Address address: addressEntity) {
			
			Long id = address.getId();
			String addressLine1 = address.getAddressLine1();
			String addressLine2 = address.getAddressLine2();
			Byte addressType = address.getAddressType();
			String city = address.getCity();
			State state = address.getStateBean();
			Country country = address.getCountryBean();
			String phoneNumber = address.getPhoneNumber();
			String zipCode = address.getZipcode();
			Byte isDefault = address.getIsDefault();
			
			CountryBeanDTO countryDTO = new CountryBeanDTO(country.getId().intValue(), country.getName());
			StateBeanDTO stateDTO = null;
			if(state != null) {
				stateDTO = new StateBeanDTO(state.getId().intValue(), state.getName());
			}
			
			
			addressesList.add(new AddressDTO(id, addressLine1, addressLine2, addressType, city, phoneNumber, zipCode, stateDTO, countryDTO, isDefault));
		}
		
		Page<AddressDTO> addressesPage = new PageImpl<AddressDTO>(addressesList);
		return addressesPage;
	}
	
/*	public static Set<AddressDTO> toAddressDTO(Set<Address> addressList){
		Set<AddressDTO> addressDTOList= new HashSet<AddressDTO>();
		for(Address address: addressList){
			AddressDTO  addressDTO = new AddressDTO();
			addressDTO.setAddressLine1(address.getAddressLine1());
			addressDTO.setAddressLine2(address.getAddressLine2());
			addressDTO.setAddressType(address.getAddressType());
			addressDTO.setCity(address.getCity());
			CountryBeanDTO cBean = new CountryBeanDTO();
			cBean.setId(address.getCountryBean().getId().intValue());
			cBean.setName(address.getCountryBean().getName());
			addressDTO.setCountryBean(cBean);
			addressDTO.setId(address.getId());
			addressDTO.setIsDefault(address.getIsDefault());
			addressDTO.setPhoneNumber(address.getPhoneNumber());
			StateBeanDTO sBean =null;
			if(address.getStateBean()!=null) {
				sBean = new StateBeanDTO();
				sBean.setId(address.getStateBean().getId().intValue());
				sBean.setName(address.getStateBean().getName());
			}
			addressDTO.setStateBean(sBean);
			addressDTO.setZipcode(address.getZipcode());			
			addressDTOList.add(addressDTO);
			
		}
		
		return addressDTOList;
	}*/
	public static AddressDTO toAddressDTO(Address address){
		AddressDTO addressDTO = new AddressDTO();
		addressDTO.setAddressLine1(address.getAddressLine1());
		addressDTO.setAddressLine2(address.getAddressLine2());
		addressDTO.setAddressType(address.getAddressType());
		addressDTO.setCity(address.getCity());
		CountryBeanDTO countryBeanDTO = new CountryBeanDTO();
		countryBeanDTO.setId(address.getCountryBean().getId().intValue());
		countryBeanDTO.setName(address.getCountryBean().getName());
		addressDTO.setCountryBean(countryBeanDTO);
		addressDTO.setId(address.getId());
		addressDTO.setIsDefault(address.getIsDefault());
		addressDTO.setPhoneNumber(address.getPhoneNumber());
		StateBeanDTO stateBeanDTO = null;
		if(address.getStateBean()!=null) {
			stateBeanDTO = new StateBeanDTO();
			stateBeanDTO.setId(address.getStateBean().getId().intValue());
			stateBeanDTO.setName(address.getStateBean().getName());	
		}
		addressDTO.setStateBean(stateBeanDTO);
		addressDTO.setZipcode(address.getZipcode());
		return addressDTO;
	}
	
/*	public static Address toAddress(AddressDTO addressDTO,long userId){
		
		Address address = new Address();
		address.setAddressLine1(addressDTO.getAddressLine1());
		address.setAddressLine2(addressDTO.getAddressLine2());
		address.setAddressType(addressDTO.getAddressType());
		address.setCity(addressDTO.getCity());
		Country country = new Country();
		country.setId(addressDTO.getCountryBean().getId().longValue());
		country.setName(addressDTO.getCountryBean().getName());
		address.setCountryBean(country);
		address.setId(addressDTO.getId());
		address.setIsDefault((byte)addressDTO.getIsDefault());
		User user = new User();
		user.setId(userId);
		address.setUser(user);
		address.setPhoneNumber(addressDTO.getPhoneNumber());
		State state = null;
		if(addressDTO.getStateBean()!=null) {
			state = new State();
			state.setId(addressDTO.getStateBean().getId().longValue());
			state.setName(addressDTO.getStateBean().getName());
		}
		
		address.setStateBean(state);
		address.setZipcode(addressDTO.getZipcode());
		return address;
	}*/
	public static Address toAddress(AddressDTO addressDTO){
		
		Address address = new Address();
		address.setAddressLine1(addressDTO.getAddressLine1());
		address.setAddressLine2(addressDTO.getAddressLine2());
		address.setAddressType(addressDTO.getAddressType());
		address.setCity(addressDTO.getCity());
		Country country = new Country();
		country.setId(addressDTO.getCountryBean().getId().longValue());
		country.setName(addressDTO.getCountryBean().getName());
		address.setCountryBean(country);
		address.setId(addressDTO.getId());
		address.setIsDefault((byte)addressDTO.getIsDefault());
		address.setPhoneNumber(addressDTO.getPhoneNumber());
		State state= null;
		if(addressDTO.getStateBean()!=null) {
		state = new State();
		state.setId(addressDTO.getStateBean().getId().longValue());
		state.setName(addressDTO.getStateBean().getName());
		}
		address.setStateBean(state);
		address.setZipcode(addressDTO.getZipcode());
		return address;
	}
	public static User toUser(SignUpFormDTO signupForm) {
		List<AddressDTO> addressDTOs = signupForm.getAddresses();
		Set<Address> addresses = new HashSet<Address>();
		Address address = new Address();
		User dao = new User();
		for(int i=0;i<addressDTOs.size();i++) {
			address = ETDUtils.toAddress( addressDTOs.get(i)) ;
			dao.addAddress(address);	
		}
			
		
    	dao.setEmailAddress(signupForm.getEmailAddress());
    	dao.setFirstName(signupForm.getFirstName());
    	dao.setLastName(signupForm.getLastName());
    	dao.setPassword(signupForm.getPassword());    
    	//dao.setAddresses(addresses);

    	return dao;
    }
	
	public static UserPaymentCard toUserPaymentCard(UserPaymentCardDTO userCardDto) {
		UserPaymentCard userPaymentCard = new UserPaymentCard();
		userPaymentCard.setCardNumber(userCardDto.getCardNumber());
		userPaymentCard.setIsDefault(userCardDto.getIsDefault());

		PaymentType p = new PaymentType();
		p.setId(userCardDto.getPaymentTypeId());
		userPaymentCard.setPaymentType(p);
		
		return userPaymentCard;
	}
	
	public static UserPaymentCardDTO toUserPaymentCardDTO(UserPaymentCard entity) {
		UserPaymentCardDTO userPaymentCardDTO = new UserPaymentCardDTO();
		return userPaymentCardDTO;
	}

}
