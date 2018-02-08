package com.nisum.user.test.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.user.controller.AuthorizationController;
import com.nisum.user.service.UserService;



@WebMvcTest(value = AuthorizationController.class, secure = false)
public class AuthorizationControllerTest extends BaseControllerTest {

	@MockBean
	private UserService userService;
	
	private final String AUTHORIZATION_URL = "/authorize";
	
	@Test
	public void userAuthorizationByTokenShouldGetUserDTO() throws Exception{
		UserDTO userDTO = new UserDTO();
		userDTO.setAuthToken("d5f299c3-1c3e-49a3-a207-29b258155247");
		userDTO.setId(1L);
		userDTO.setTokenValidity(1L);
		userDTO.setUserName("test");
		userDTO.setUpdatedAt(1L);
		Mockito.when(userService.findUserByToken(Mockito.anyString())).thenReturn(userDTO);
		 mvc.perform(MockMvcRequestBuilders.post(AUTHORIZATION_URL+"/token")
				 .contentType(MediaType.APPLICATION_JSON)
					.header("auth-token", "d5f299c3-1c3e-49a3-a207-29b258155247"))
					.andExpect(status().isOk()).andReturn();
	}
}
