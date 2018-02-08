package com.nisum.user.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.nisum.common.constant.AppConstant;
import com.nisum.user.model.Country;
import com.nisum.user.model.State;
import com.nisum.user.repository.StateRepository;
import com.nisum.user.service.CountryService;
import com.nisum.user.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service("stateService")
public class StateServiceImpl implements StateService {

	@Autowired
	StateRepository stateRepository;
	
	@Autowired
	CountryService countryService;
	
	@Autowired
	RestTemplate restTemplate;

	@Transactional(readOnly = true)
	@Override
	public List<State> getStates(Long countryId) {
		return stateRepository.findByCountryId(countryId);
	}

	@Transactional(readOnly = true)
	@Override
	public State findStateById(Long id) {
		return stateRepository.findOne(id);
	}

	@Transactional(readOnly = true)
	@Override
	public List<State> getStateByName(String name) {
		return stateRepository.findByNameContaining(name);
	}

	@Override
	public Object getStateByCountryIdOrName(Long countryId, Long id, String name) {
		Object returnObject = null;
		if (id != null) {
			State state = this.findStateById(id);
			if(Objects.isNull(state)) throw new EmptyResultDataAccessException(1);
			returnObject = state;
		} else if (name != null) {
			List<State> stateList = this.getStateByName(name);

			if (stateList == null || stateList.size() == 0) {
				throw new EmptyResultDataAccessException(1);
			}

			returnObject = stateList;
		} else {
			List<State> stateList = this.getStates(countryId);
				if(stateList.size()==0) throw new EmptyResultDataAccessException(1);
			returnObject = stateList;
		}

		return returnObject;
	}

	@Override
	public String validateZipCode(String zipcode, String city, String state, String country) throws IOException {
		String response = null;
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
		headers.add("user-agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		String url = AppConstant.ZIP_CODE_VALIDATION_SERVICE + zipcode;
		ResponseEntity<String> res = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

		String strResponse = res.getBody();

		if (strResponse.indexOf("error") > 0) {
			return strResponse;
		}

		ObjectMapper objectMapper = new ObjectMapper();

		JsonNode jsonObject = objectMapper.readTree(strResponse);

		String countryFromService = jsonObject.get("country").asText();
		String stateFromService = jsonObject.get("state").asText();
		String cityFromService = jsonObject.get("city").asText();

		state = this.findStateById(Long.parseLong(state)).getAbbreviation();

		Country findById = countryService.findById(Long.parseLong(country));

		if (findById != null){
            country = findById.getAbbreviation();
        }

		if (country.equalsIgnoreCase(countryFromService) && state.equalsIgnoreCase(stateFromService) && city.equalsIgnoreCase(cityFromService)) {
            ObjectMapper json = new ObjectMapper();
            ObjectNode root = json.createObjectNode();
            root.put("valid", true);
            response = json.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        }else {
            ObjectMapper json = new ObjectMapper();
            ObjectNode root = json.createObjectNode();
            root.put("error", true);
            response = json.writerWithDefaultPrettyPrinter().writeValueAsString(root);
        }

		return response;
	}
}
