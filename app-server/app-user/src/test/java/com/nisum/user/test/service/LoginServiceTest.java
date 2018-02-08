package com.nisum.user.test.service;

import com.nisum.common.exception.rest.RestException;
import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.user.dto.LoginDTO;
import com.nisum.user.dto.TokenDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.LoginService;
import com.nisum.user.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertNotNull;

/**
 * Testing the class LoginServiceImp
 * 
 */

public class LoginServiceTest extends BaseServiceTest {

	@Autowired
	private LoginService loginService;

	@MockBean
	private UserService userService;

	/**
	 * Testing loginService.login()
	 */
	@Test
	public void shouldReturnLoginDTO() {
		
		User user = new User();
		user.setFirstName("Asfar");
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmailAddress("abcd@nisum.com");
		loginDTO.setPassword("asdfasdf");
		loginDTO.setGeustToken("");
		loginDTO.setRememberMe(false);
		Mockito.when(userService.findUserByEmailAddressAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(user);
		Mockito.when(userService.findUserByToken(Mockito.anyString())).thenReturn(new UserDTO());
		Mockito.when(userService.update(Mockito.any(User.class))).thenReturn(user);
		TokenDTO tokenDTO = loginService.login(loginDTO);
		assertNotNull(tokenDTO);
	}
	
	@Test(expected = RestException.class)
	public void loginShouldThrowRestException() {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setEmailAddress("abcd@nisum.com");
		loginDTO.setPassword("asdfasdf");
		loginDTO.setGeustToken("");
		loginDTO.setRememberMe(false);
		Mockito.when(userService.findUserByEmailAddressAndPassword(Mockito.anyString(), Mockito.anyString())).thenReturn(null);
		Mockito.when(userService.findUserByToken(Mockito.anyString())).thenReturn(new UserDTO());
		Mockito.when(userService.update(new User())).thenReturn(new User());
		TokenDTO user = loginService.login(loginDTO);
		assertNotNull(user);
	}
	
	@Test(expected = RestException.class)
	public void logoutShouldThrouhgRestException(){
		Long userid = 1l;
		Mockito.when(userService.findOne(Mockito.anyLong())).thenReturn(new User());
		Mockito.when(userService.update(new User())).thenReturn(new User());
		loginService.logout(userid);
	}
	
	@Test
	public void shouldLogout(){
		
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1L);
		
		User user = new User();
		user.setId(1L);
		
				
		Mockito.when(userService.findOne(Mockito.anyLong())).thenReturn(user);
		Mockito.when(userService.findUserByToken(Mockito.anyString())).thenReturn(userDTO);
		Mockito.when(userService.update(new User())).thenReturn(new User());
		
		loginService.logout(1l);
	}
}