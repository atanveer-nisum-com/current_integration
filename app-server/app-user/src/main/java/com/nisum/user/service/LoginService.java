package com.nisum.user.service;

import com.nisum.user.dto.LoginDTO;
import com.nisum.user.dto.TokenDTO;


/**
 * The Interface LoginService.
 */
public interface LoginService {
	/**
	 * Login
	 * @param loginDTO the LoginDTO
	 * @param clientid the clientid
	 * @return the TokenDTO
	 */
	
	public TokenDTO login(LoginDTO loginDTO);
	
	public Integer logout(Long userid);
	
}
