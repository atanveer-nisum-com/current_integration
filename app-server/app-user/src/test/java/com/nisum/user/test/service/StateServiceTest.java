package com.nisum.user.test.service;

import com.nisum.user.model.Country;
import com.nisum.user.model.State;
import com.nisum.user.repository.CountryRepository;
import com.nisum.user.repository.StateRepository;
import com.nisum.user.service.CountryService;
import com.nisum.user.service.StateService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.*;

// TODO: Auto-generated Javadoc
/**
 * The Class StateServiceTest.
 */

public class StateServiceTest extends BaseServiceTest {

	@Autowired
	private StateRepository stateRepository;

	@Autowired
	private StateService stateService;

	@Autowired
	private CountryService countryService;

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private RestTemplate restTemplate;

	/**
	 * Should get states by country id.
	 */
	@Test
	public void shouldGetStatesByCountryId() {
		
		
		State state = this.getMockState();
		List<State> statesList = new ArrayList<State>();
		statesList.add(state);
		
		List<State> persistedStatesList;
		
		
		Mockito.when(stateRepository.findByCountryId(anyLong())).thenReturn(statesList);
		
		
		persistedStatesList = stateService.getStates(1L);
		
		assertTrue(persistedStatesList!=null && persistedStatesList.size()>0);
		
	}
	
	
	/**
	 * Should find state by id.
	 */
	@Test
	public void shouldFindStateById() {
		State state = this.getMockState();
		State persistedState;
		
		Mockito.when(stateRepository.findOne(anyLong())).thenReturn(state);
		
		
		persistedState = stateService.findStateById(1L);
		
		
		assertTrue(persistedState.getAbbreviation().equals("NY"));
		
	}
	
	/**
	 * Should get state by name.
	 */
	@Test
	public void shouldGetStateByName() {
		State state = this.getMockState();
		List<State> statesList = new ArrayList<State>();
		statesList.add(state);
		
		List<State> persistedStatesList;
		
		Mockito.when(stateRepository.findByNameContaining(anyString())).thenReturn(statesList);
		
		persistedStatesList = stateRepository.findByNameContaining("New York");
		
		
		
		
		assertTrue(persistedStatesList!=null && persistedStatesList.size()>0);
	}

	
	/**
	 * Should get state by country id or name.
	 */
	@Test
	public void shouldGetStateByCountryIdOrName() {
		
		State state = this.getMockState();
		List<State> statesList = new ArrayList<State>();
		statesList.add(state);
		
		Object persistedStatesList;
		
		Mockito.when(stateRepository.findOne(anyLong())).thenReturn(state);
		Mockito.when(stateRepository.findByNameContaining(anyString())).thenReturn(statesList);
		Mockito.when(stateRepository.findByCountryId(anyLong())).thenReturn(statesList);
		
		//Try different combinations to run all scenarios
		
		persistedStatesList = stateService.getStateByCountryIdOrName(1L, 1L, "NY");
		assertTrue(persistedStatesList!=null);
		persistedStatesList = null;
		
		persistedStatesList = stateService.getStateByCountryIdOrName(null, 1L, "NY");
		assertTrue(persistedStatesList!=null);
		persistedStatesList = null;
		
		persistedStatesList = stateService.getStateByCountryIdOrName(1L, null, "NY");
		assertTrue(persistedStatesList!=null);
		persistedStatesList = null;
		
		persistedStatesList = stateService.getStateByCountryIdOrName(null, null, null);
		assertTrue(persistedStatesList!=null);
		persistedStatesList = null;
		
		persistedStatesList = stateService.getStateByCountryIdOrName(1L, 1L, null);
		assertTrue(persistedStatesList!=null);
		
	}
	
	
	/**
	 * Should validate zip code.
	 * @throws IOException 
	 */
	@Test
	public void shouldValidateZipCode() throws IOException {
		
		State state = this.getMockState();
		List<State> statesList = new ArrayList<State>();
		statesList.add(state);
		Country country = this.getMockCountry();
		String strResult="";
		
		
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		

		ResponseEntity<String> mockedZipCodeValidation = this.mockZipCodeValidation();
				
		Mockito.when(countryService.findById(anyLong())).thenReturn(country);
		Mockito.when(stateRepository.findOne(anyLong())).thenReturn(state);
		Mockito.when(stateRepository.findByNameContaining(anyString())).thenReturn(statesList);
		Mockito.when(stateRepository.findByCountryId(anyLong())).thenReturn(statesList);
		//Mockito.when(restTemplate.exchange(anyString(),anyObject(), anyObject(), String.class)).thenReturn(mockedZipCodeValidation);
		Mockito.when(restTemplate.exchange(anyString(),eq(HttpMethod.GET), eq(entity), eq(String.class))).thenReturn(mockedZipCodeValidation);
		//Mockito.when(restTemplate.exchange(anyString(), anyObject(), anyObject(), eq(String.class))).thenReturn(mockedZipCodeValidation);
		//Try different combinations to run all scenarios
		strResult = stateService.validateZipCode("74700", "NY City", "2222", "1111");
		assert(!strResult.isEmpty());
		strResult = "";
		
		strResult = stateService.validateZipCode("10005", "NEW YORK", "1", "1");
		assert(!strResult.isEmpty());
		strResult = "";
		
		strResult = stateService.validateZipCode("10005", "NY CITY", "1", "1");
		assert(!strResult.isEmpty());
	}
	
	
	
	private ResponseEntity<String> mockZipCodeValidation() {
		
		String strBody = "{\r\n" + 
				"  \"country\": \"US\",\r\n" + 
				"  \"state\": \"NY\",\r\n" + 
				"  \"city\": \"NEW YORK\"\r\n" + 
				"}";
		ResponseEntity<String> result = new ResponseEntity<String>(strBody, HttpStatus.OK);
		
	
		
		return result;
		
	}


	/**
	 * Gets the mock state.
	 *
	 * @return the mock state
	 */
	//Mock Object Methods
	private State getMockState() {
		
		State state = new State();
		
		state.setId(1L);
		state.setAbbreviation("NY");
		state.setName("New York");
		state.setCountry(this.getMockCountry());
		
		return state;
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
}
