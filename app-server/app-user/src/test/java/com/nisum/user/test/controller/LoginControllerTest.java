package com.nisum.user.test.controller;

import com.nisum.common.exception.rest.RestException;
import com.nisum.user.controller.LoginController;
import com.nisum.user.dto.LoginDTO;
import com.nisum.user.dto.TokenDTO;
import com.nisum.user.service.LoginService;
import org.junit.Test;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

/**
 * Class LoginControllerTest for unit testing of LoginController 
 * using Mockito to mock services on which the controller has depends
 * @author tdas
 *
 */

@WebMvcTest(value = LoginController.class, secure = false)
public class LoginControllerTest extends BaseControllerTest{
	
	@MockBean 
	private LoginService loginService;
	private final String LOGIN_URL_POST =BaseControllerTest.BASE_URL_APP_USERS +"/login";
	private final String LOGOUT_URL_POST =BaseControllerTest.BASE_URL_APP_USERS + "/logout";
	private final String mockUser = "{\"emailAddress\":\"tdas@nisum.com\", \"password\":\"asdfasdf\",\"remeberMe\":false, \"guestToken\":\"\" }";
	/**
	 * Test method to test the l
	 * 
	 **/

	@Test
	public void ShouldReturnTokenDTO() throws Exception {
		TokenDTO tokenDTO = new TokenDTO();
		tokenDTO.setToken("87878787-89889");
		Mockito.when(loginService.login(Mockito.any(LoginDTO.class))).thenReturn(tokenDTO);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(LOGIN_URL_POST).accept(MediaType.APPLICATION_JSON).content(mockUser).contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("token").value("87878787-89889")).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void Logout() throws Exception {
		Mockito.when(loginService.logout(Matchers.anyLong())).thenReturn(1);
		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(LOGOUT_URL_POST).accept(MediaType.APPLICATION_JSON)
				.header("userId", 1L).contentType(MediaType.APPLICATION_JSON)).andReturn();
		assertEquals(HttpStatus.NO_CONTENT.value(), result.getResponse().getStatus());
	}
	
	@Test
	public void isServiceNotNull() throws Exception {
		assertNotNull(loginService);
	}
}
