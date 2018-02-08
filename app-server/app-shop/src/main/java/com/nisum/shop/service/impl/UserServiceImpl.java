package com.nisum.shop.service.impl;

import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.shop.dto.RegistrationDTO;
import com.nisum.shop.model.User;
import com.nisum.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	RestTemplate restClient;
	
	@Override
	public User findOne(Long id) {
//		String restEndPoint = String.format(CommonEndPointConstant.USER_FIND_ONE , id);
		User user = restClient.getForObject(CommonEndPointConstant.USER_FIND_ONE, User.class,id);
		return user;
	}

	@Override
	public Boolean isAlive(String userId, String accessToken) {
		String restEndPoint = String.format(CommonEndPointConstant.USER_IS_ALIVE +"/%s/%s", userId, accessToken);
		Boolean response = restClient.getForObject(restEndPoint, Boolean.class);
		return response;
	}

	@Override
	public Boolean update(RegistrationDTO registrationDTO) {
		ResponseEntity<Boolean> response = restClient.postForEntity(CommonEndPointConstant.CHECKOUT_SAVE_USER, registrationDTO, Boolean.class);
		
		return response.getBody();
	}

	@Override
	public User saveGuest() {
		User user = restClient.getForObject(CommonEndPointConstant.SAVE_GUEST, User.class);
		return user;
	}

}
