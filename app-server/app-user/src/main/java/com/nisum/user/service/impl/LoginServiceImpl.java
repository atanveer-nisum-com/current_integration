package com.nisum.user.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.util.AESEncryptionUtils;
import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.LoginDTO;
import com.nisum.user.dto.TokenDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.LoginService;
import com.nisum.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("loginService")
public class LoginServiceImpl implements LoginService {

	@Autowired
	private UserService userService;
	
	@Override
	public TokenDTO login(LoginDTO loginDTO) {
		User userModel = userService.findUserByEmailAddressAndPassword(loginDTO.getEmailAddress(), AESEncryptionUtils.encrypt((loginDTO.getPassword())));
		if(userModel == null)
			throw new EmptyResultDataAccessException(1);
		String token = null;
		if(userModel.getTokenValidity()!=null && userModel.getTokenValidity()>DateUtils.getCurrentTime()) {
			token = userModel.getAuthToken();
		}
		else{
			token = UUID.randomUUID().toString();
			userModel.setAuthToken(token);	
		}
		
		if(null != loginDTO.getRememberMe()) {
			userModel.setTokenValidity(DateUtils.getCurrentTime() + DateUtils.convertDaysIntoSeconds(AppConstant.LOGIN_REMEMBER_ME_TOKEN_VALIDITY_IN_DAYS));
		} else {
			userModel.setTokenValidity(DateUtils.getCurrentTime() + DateUtils.convertDaysIntoSeconds(AppConstant.LOGIN_TOKEN_VALIDITY_IN_DAYS));
		}
		
		userModel.setUpdatedAt(DateUtils.getCurrentTime());
		TokenDTO tokenDTO = new TokenDTO();
		userModel = userService.update(userModel);
		tokenDTO.setToken(token);
		tokenDTO.setFirstName(userModel.getFirstName());
		return tokenDTO;
	}

	@Override
	public Integer logout(Long userid) {
		User user = userService.findOne(userid);
		if (user.getId() == null)
			throw new EmptyResultDataAccessException(1);
		user.setAuthToken(null);
		user.setTokenValidity(DateUtils.getCurrentTime());
		user.setUpdatedAt(DateUtils.getCurrentTime());
		user = userService.update(user);
		if (null != user) return 1;
		throw new DataRetrievalFailureException("No user exists");
	}
}
