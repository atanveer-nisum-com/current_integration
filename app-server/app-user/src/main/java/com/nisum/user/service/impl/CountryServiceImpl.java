package com.nisum.user.service.impl;

import com.nisum.user.model.Country;
import com.nisum.user.repository.CountryRepository;
import com.nisum.user.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("countryService")
public class CountryServiceImpl implements CountryService {

	@Autowired
	CountryRepository countryRepository;

	@Transactional(readOnly = true)
	public List<Country> getCountries() {
		return countryRepository.findAll();
	}

	@Override
	public Country findById(Long id) {
		return countryRepository.findOne(id);
	}

	@Override
	public List<Country> findByName(String name) {
		return countryRepository.findByNameContaining(name);
	}

	@Override
	public Object getCountryByIdOrName(Long id, String name) {
		Object returnObject = null;
		if (id != null) {
			Country country = this.findById(id);

			if (country == null) {

				throw new EmptyResultDataAccessException(1);
			}

			returnObject = country;
		} else if (name != null) {
			List<Country> countryList = this.findByName(name);

			if (countryList == null || countryList.size() == 0) {

				throw new EmptyResultDataAccessException(1);
			}

			returnObject = countryList;
		} else {
			List<Country> countryList = this.getCountries();

			if (countryList == null || countryList.size() == 0) {

				throw new EmptyResultDataAccessException(1);
			}
			returnObject = countryList;
		}

		return returnObject;
	}

}
