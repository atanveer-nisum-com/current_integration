package com.nisum.shop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nisum.common.constant.CommonEndPointConstant;
import com.nisum.common.shop.dto.RegistrationDTO;
import com.nisum.common.shop.dto.UserDTO;
import com.nisum.shop.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	RestTemplate restClient;
	
	@Override
	public UserDTO findOne(Long id) {
//		String restEndPoint = String.format(CommonEndPointConstant.UserDTO_FIND_ONE , id);
		UserDTO userDTO = restClient.getForObject(CommonEndPointConstant.USER_FIND_ONE, UserDTO.class,id);
		return userDTO;
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
	public UserDTO saveGuest() {
		UserDTO UserDTO = restClient.getForObject(CommonEndPointConstant.SAVE_GUEST, UserDTO.class);
		return UserDTO;
	}

}
