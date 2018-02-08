/**
 * 
 */
package com.nisum.user.controller;

import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nisum pakistan
 *
 */
@RestController
@RequestMapping("authorize")
public class AuthorizationController {

	@Autowired
	private UserService userService;
	
	@PostMapping(value="/token")
	public UserDTO userAuthorizationByToken(@RequestHeader("auth-token") String token) {
		return userService.findUserByToken(token);
	}
}
