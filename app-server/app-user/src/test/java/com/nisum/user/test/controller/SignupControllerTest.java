package com.nisum.user.test.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.user.controller.SignupController;
import com.nisum.user.dto.*;
import com.nisum.user.model.Address;
import com.nisum.user.model.User;
import com.nisum.user.service.EmailService;
import com.nisum.user.service.UserService;
import com.nisum.user.util.UserConverter;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = SignupController.class, secure = false)
public class SignupControllerTest extends BaseControllerTest {
	

	private final String SIGN_UP_POST_URL= "/users/signup";
	
	@Autowired
	SignupController signupContoller;
	@MockBean
	private EmailService emailService;
	@MockBean
	private RestTemplate restTemplate;
	
	@MockBean
	protected UserService userService;
	@Autowired
	private UserConverter userConverter;
	
	User mockUser = new User();
//	State mockState  = new State();
//	Country mockCountry = new Country();
	TokenDTO mockTokenDTO = new TokenDTO();
	RestResponseDTO mockRestResponseDTO;

	String exampleSignupDTO = "{\r\n" + 
			"  \"firstName\": \"test\",\r\n" + 
			"  \"lastName\": \"test\",\r\n" + 
			"  \"emailAddress\": \"abcd@haskjd.com\",\r\n" + 
			"  \"password\": \"1234567\",\r\n" + 
			"  \"addresses\": [\r\n" + 
			"    {\r\n" + 
			"      \"addressLine1\": \"abcdefgh\",\r\n" + 
			"      \"addressLine2\": \"ijklmnop\",\r\n" + 
			"      \"addressType\": \"0\",\r\n" + 
			"      \"city\": \"los angeles\",\r\n" + 
			"      \"phoneNumber\": \"09876543210\",\r\n" + 
			"      \"zipcode\": \"90002\",\r\n" + 
			"      \"stateBean\": {\r\n" + 
			"        \"id\": \"5\"\r\n" + 
			"      },\r\n" + 
			"      \"countryBean\": {\r\n" + 
			"        \"id\": \"230\"\r\n" + 
			"      }\r\n" + 
			"    }\r\n" + 
			"  ]\r\n" + 
			"}";
		
	
	@Test
	public void signUpShouldReturnToken() throws Exception {
		mockUser.setFirstName("test");
		mockUser.setLastName("test");
		mockUser.setEmailAddress("abcd@haskjd.com");
		Set<Address> addresses = new HashSet<Address>();
		Address address = new Address();
		address.setAddressLine1("abcdefg");
		address.setAddressLine2("ijklmnop");
		address.setCity("los angeles");
		address.setPhoneNumber("09876543210");
		address.setZipcode("90002");
		addresses.add(address);
		mockUser.setAddresses(addresses);
		mockUser.setAuthToken("testtoken");
		
		Mockito.when(userService.createUser(Mockito.anyObject())).thenReturn(mockUser);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(SIGN_UP_POST_URL)
				.accept(MediaType.APPLICATION_JSON).content(exampleSignupDTO)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("token").value("testtoken")); 
		
		
	}
	@Test
	public void signUpShouldReturnTokenWithGuestNotNull() throws Exception {
	
		mockUser.setFirstName("test");
		mockUser.setLastName("test");
		mockUser.setEmailAddress("abcd@haskjd.com");
		Set<Address> addresses = new HashSet<Address>();
		Address address = new Address();
		address.setAddressLine1("abcdefg");
		address.setAddressLine2("ijklmnop");
		address.setCity("los angeles");
		address.setPhoneNumber("09876543210");
		address.setZipcode("90002");
		addresses.add(address);
		mockUser.setAddresses(addresses);
		mockUser.setAuthToken("testtoken");
		
		
		
		Mockito.when(userService.createUser(Mockito.anyObject())).thenReturn(mockUser);
		Mockito.when(userService.update(Mockito.anyObject())).thenReturn(mockUser);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(SIGN_UP_POST_URL)
				.accept(MediaType.APPLICATION_JSON).content(getSignUpFormDTO())
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		mvc.perform(asyncDispatch(result))
				.andExpect(status().isOk())
		        .andExpect(jsonPath("token").value("testtoken")); 
		
		
	}
	
	private String getSignUpFormDTO() throws JsonProcessingException {
		StateBeanDTO state = new StateBeanDTO();
		state.setId(5);
		CountryBeanDTO country = new CountryBeanDTO();
		country.setId(230);
		List<AddressDTO> addresses = new ArrayList<AddressDTO>();
		SignUpFormDTO signupFormDTO = new SignUpFormDTO();
		AddressDTO addressesDTO = new AddressDTO();
		signupFormDTO.setFirstName("test"); 
		signupFormDTO.setLastName("test"); 
		signupFormDTO.setEmailAddress("abcd@haskjd.com"); 
		signupFormDTO.setPassword("1234567");
		signupFormDTO.setGuestId(1l);
		
		addressesDTO.setAddressLine1("abcdefgh");
		addressesDTO.setAddressLine2("ijklmnop"); 
		addressesDTO.setAddressType((byte)0);
		addressesDTO.setCity("los angeles");
		addressesDTO.setPhoneNumber("09876543210");
		addressesDTO.setZipcode("90002");
		addressesDTO.setStateBean(state); 
		addressesDTO.setCountryBean(country);
		addresses.add(addressesDTO);
		signupFormDTO.setAddresses(addresses);
		return new ObjectMapper()
	            .writeValueAsString(signupFormDTO);
		
		
	}
	

}
