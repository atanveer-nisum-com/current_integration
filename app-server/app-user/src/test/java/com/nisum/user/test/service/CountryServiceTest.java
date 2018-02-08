package com.nisum.user.test.service;

import com.nisum.user.model.Country;
import com.nisum.user.repository.CountryRepository;
import com.nisum.user.service.CountryService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Matchers.anyLong;
import static org.mockito.Matchers.anyString;

// TODO: Auto-generated Javadoc
/**
 * The Class CountryServiceTest.
 */

public class CountryServiceTest extends BaseServiceTest {

	@Autowired
	private CountryRepository countryRepository;

	@Autowired
	private CountryService countryService;

	/**
	 * Should get countries list.
	 */
	@Test
	public void shouldGetCountriesList() {
		List<Country> listCountryMock = this.getMockCountries();
		List<Country> persistedCountriesList;
		
		Mockito.when(countryRepository.findAll()).thenReturn(listCountryMock);
		
		
		persistedCountriesList = countryService.getCountries();
		
		assert(persistedCountriesList.size()>0);
		
	}
	
	/**
	 * Should retrieve country by id.
	 */
	@Test
	public void shouldRetrieveCountryById() {
		Country countryMock = this.getMockCountry();
		Country persistedCountry;
		
		Mockito.when(countryRepository.findOne(anyLong())).thenReturn(countryMock);
		
		persistedCountry = countryService.findById(100L);
		
		assert(persistedCountry.getAbbreviation().equals("AU"));
		
	}
	
	/**
	 * Should retrieve country by name.
	 */
	@Test
	public void shouldRetrieveCountryByName() {
		
		List<Country> listCountryMock = this.getMockCountries();
		List<Country> persistedCountriesList;
		
		Mockito.when(countryRepository.findByNameContaining(anyString())).thenReturn(listCountryMock);
		
		persistedCountriesList = countryService.findByName("Pakistan");
		
		assert(persistedCountriesList.size()>0);
	}
	
	/**
	 * Should retrieve country by id or name.
	 */
	@Test
	public void shouldRetrieveCountryByIdOrName() {
		
		Country countryMock = this.getMockCountry();
		List<Country> listCountryMock = this.getMockCountries();
		Object objCountries;
		
		//Mock the methods of repository that would be used in this method
		Mockito.when(countryRepository.findOne(anyLong())).thenReturn(countryMock);
		Mockito.when(countryRepository.findByNameContaining(anyString())).thenReturn(listCountryMock);
		Mockito.when(countryRepository.findAll()).thenReturn(listCountryMock);
		
		//first try with ID
		objCountries = countryService.getCountryByIdOrName(1L,"");
		assert(objCountries!=null);
		objCountries = null;
		
		//Now try with Name
		objCountries = countryService.getCountryByIdOrName(null,"Pakistan");
		assert(objCountries!=null);
		objCountries = null;
		
		
		//Now try with both NULL parameters
		objCountries = countryService.getCountryByIdOrName(null,null);
		assert(objCountries!=null);
		
		
	}
	
	@Test
	public void shouldThrowExceptionWhenFindingCountryByIdOrName() {
		
		//Country countryMock = this.getMockCountry();
		//List<Country> listCountryMock = this.getMockCountries();
		Object objCountries;
		
		//Mock the methods of repository that would be used in this method
		Mockito.when(countryRepository.findOne(anyLong())).thenReturn(null);
		Mockito.when(countryRepository.findByNameContaining(anyString())).thenReturn(null);
		Mockito.when(countryRepository.findAll()).thenReturn(null);
		
		//first try with ID
		try {
			
			objCountries = countryService.getCountryByIdOrName(1L,"");
			
		}
		catch(Exception ex) {
			assert(ex.getMessage().contains("No country found"));
		}
		//Now try with Name
		try {
			objCountries = countryService.getCountryByIdOrName(null,"Pakistan");
			
		}
		catch(Exception ex) {
			assert(ex.getMessage().contains("No country found"));
		}
		
		//Now try with both NULL parameters
		try {
			objCountries = countryService.getCountryByIdOrName(null,null);
			
		}
		catch(Exception ex) {
			assert(ex.getMessage().contains("Country list"));
		}
		
	}
	
	
	/**
	 * Gets the mock countries.
	 *
	 * @return the mock countries
	 */
	private List<Country> getMockCountries() {
		
		List<Country> listCountry = new ArrayList<Country>();
		
		listCountry.add(this.getMockCountry(1L, "USA", "US"));
		listCountry.add(this.getMockCountry(2L, "Pakistan", "PK"));
		listCountry.add(this.getMockCountry(3L, "Bahrain", "BH"));
		
		
		return listCountry;
	}
	
	/**
	 * Gets the mock country.
	 *
	 * @param id the id
	 * @param name the name
	 * @param abbreviation the abbreviation
	 * @return the mock country
	 */
	private Country getMockCountry(Long id, String name, String abbreviation) {
		
		Country country = new Country();
		
		country.setId(id);
		country.setAbbreviation(abbreviation);
		country.setName(name);
		
		
		return country;
		
	}
	
	/**
	 * Gets the mock country.
	 *
	 * @return the mock country
	 */
	private Country getMockCountry() {
		
		Country country = new Country();
		
		country.setId(100L);
		country.setAbbreviation("AU");
		country.setName("Australia");
		
		
		return country;
		
	}

}