package com.nisum.user.controller;


import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.service.StateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


/**
 * The Class StateController.
 */
@RestController
@RequestMapping("states")
public class StateController extends BaseController {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(StateController.class);

	/** The state service. */
	@Autowired
	private StateService stateService;


	@GetMapping("/{countryId}")
	public Object list(@PathVariable Long countryId, @RequestParam(required=false) Long id, @RequestParam(required=false) String name) {
		try {
			return stateService.getStateByCountryIdOrName(countryId, id, name);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No state found", ErrorLevel.ERROR);
		}
	}

	
	@GetMapping("/validateZip/{zipcode}/{city}/{state}/{country}")
	public String validateState(@PathVariable String zipcode, @PathVariable String city, @PathVariable String state,
			@PathVariable String country) {
		try {
			return stateService.validateZipCode(zipcode, city, state, country);
		} catch (IOException e) {
			throw new RestException(HttpStatus.INTERNAL_SERVER_ERROR, "Error occured in processing your request",
					ErrorLevel.ERROR);
		}
	}

}
