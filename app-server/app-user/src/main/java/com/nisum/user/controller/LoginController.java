package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.LoginDTO;
import com.nisum.user.dto.TokenDTO;
import com.nisum.user.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * The Class LoginController.
 */
@RestController
@RequestMapping("users")
public class LoginController extends BaseController {
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	/** The login service. */
	@Autowired
	private LoginService loginService;
	
	/**
	 * login
	 * 
	 * @param loginDTO the login DTO
	 * @return token
	 */
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenDTO login(@RequestBody LoginDTO loginDTO) {
		logger.info(String.format("Start - Post Mapping of LoginController contains %s", loginDTO));
		TokenDTO tokenDTO;
		try {
			tokenDTO =  loginService.login(loginDTO);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No user found", ErrorLevel.ERROR);
		}
		logger.info(String.format("End - Post Mapping of LoginController return token %s", tokenDTO));
		return tokenDTO;
	}
	
	/**
	 * Logout.
	 *
	 * @param userId the user id
	 */
	@PostMapping("/logout")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void logout(@RequestHeader(value = "userId", required = true) Long userId) {
		if (!loginService.logout(userId).equals(1)) throw new RestException(HttpStatus.BAD_REQUEST, "Invalid user",
				ErrorLevel.ERROR);
	}
}
