package com.nisum.user.test.controller;

import com.nisum.user.controller.CountryController;
import com.nisum.user.model.Country;
import com.nisum.user.service.CountryService;
import org.junit.Test;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

@WebMvcTest(value = CountryController.class, secure = false)
public class CountryControllerTest extends BaseControllerTest{
	
	@MockBean
	private CountryService countryService;
	
	private final String COUNTRY_URL ="/countries/?id=230";
	private final String COUNTRY_URL_ALL = "/countries";
	
	//CountryBeanDTO mockCountryBeanDTO = new CountryBeanDTO(230,"United States");
	private Country mockCountry = new Country();
	private List<Country> mockCountries = new ArrayList<Country>();
	
	
	@Test
	public void retriveDetailsForCountry() throws Exception {
		mockCountry.setId(230l);
		mockCountry.setName("United States");
		
		Mockito.when(countryService.getCountryByIdOrName(Mockito.anyLong(), Mockito.anyString())).thenReturn(mockCountry);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				COUNTRY_URL).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		System.out.println(result.getResponse().getContentAsString());
		String expected = "{id:230,name:\"United States\"}";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	@Test
	public void retriveDetailsForCountries() throws Exception {
		
		mockCountry.setId(230l);
		mockCountry.setName("United States");
		mockCountries.add(mockCountry);
		
		Mockito.when(countryService.getCountryByIdOrName(Mockito.anyLong(), Mockito.anyString())).thenReturn(mockCountries);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
				COUNTRY_URL_ALL).accept(
				MediaType.APPLICATION_JSON);
		MvcResult result = mvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		System.out.println(result.getResponse());
		String expected = "[{id:230,name:\"United States\"}]";
		JSONAssert.assertEquals(expected, result.getResponse()
				.getContentAsString(), false);
	}
	

	

}
