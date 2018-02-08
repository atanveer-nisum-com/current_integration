package com.nisum.shop.service.impl;

import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.shop.model.Country;
import com.nisum.shop.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CountryServiceImpl implements CountryService{

	@Autowired
	CountryService counterService;
	
	@Autowired
	RestTemplate restClient;
	
	@Override
	public Country findById(Long id) {
		String restUrl = String.format(CommonEndPointConstant.COUNTRY_FIND_BY_ID +"/%d", id);
		Country country = restClient.getForObject(restUrl, Country.class);
		return country;
	}

}
