package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * The Class CountryController.
 */
@RestController
@RequestMapping("countries")
public class CountryController extends BaseController {
	
	/** The country service. */
	@Autowired
	private CountryService countryService;

	/**
	 * List.
	 *
	 * @param id the id
	 * @param name the name
	 * @return the object
	 */
	@GetMapping
	public Object getCountryByIdOrName(@RequestParam(required=false) Long id, @RequestParam(required=false) String name) {
		try {
			return countryService.getCountryByIdOrName(id, name);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No country found", ErrorLevel.ERROR);
		}
	}

}
