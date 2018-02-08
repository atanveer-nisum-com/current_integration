package com.nisum.user.test.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nisum.common.constant.AppConstant;
import com.nisum.user.controller.ForgotPasswordController;
import com.nisum.user.dto.ResetPasswordDTO;
import com.nisum.user.dto.RestResponseDTO;
import com.nisum.user.service.ForgotPasswordService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(value = ForgotPasswordController.class, secure = false)
public class ForgotPasswordControllerTest extends BaseControllerTest {

	RestResponseDTO mockRestResponseDTO;
	@MockBean
	ForgotPasswordService forgotPasswordService;
	private final String FORGET_PASSWORD_URL= "/users/forgetpassword";
	private final String RESET_PASSWORD_URL= "/users/resetpassword";
	private final String RESET_PASSWORD_TOKEN_VALIDATION_URL= "/users/resetpassword/4f102ae5-1325-413b-9cdc-4fae86042141";
	
	@Test
	public void forgotPasswordShouldReturnResponseDTO() throws Exception {
		String forgetPasswordDTO = "{\"email\":\"test@test.com\"}";
		 mockRestResponseDTO =  new RestResponseDTO();
		mockRestResponseDTO.setStatus(AppConstant.REST_RESPONSE_SUCCESS);
		mockRestResponseDTO.setMessage("Check your email for a link to reset your password. If it doesn't appear within a few minutes, check your spam folder.");
		Mockito.when(forgotPasswordService.forgetPassword(Mockito.anyObject())).thenReturn(mockRestResponseDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(FORGET_PASSWORD_URL)
				.accept(MediaType.APPLICATION_JSON).content(forgetPasswordDTO)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value(AppConstant.REST_RESPONSE_SUCCESS))
				.andReturn();
				
	}			
	@Test	
	public void resetPasswordShouldReturnResponseDTO () throws Exception {
		mockRestResponseDTO  = new RestResponseDTO();
		mockRestResponseDTO.setStatus(AppConstant.REST_RESPONSE_SUCCESS);
		mockRestResponseDTO.setMessage("Your password has been reset!");
		ResetPasswordDTO resetPasswordDTO = new ResetPasswordDTO();
		resetPasswordDTO.setResetToken("4f102ae5-1325-413b-9cdc-4fae86042141");
		resetPasswordDTO.setPassword("asfar123");
		resetPasswordDTO.setConfirmPassword("asfar123");
		
		String expectedJsonString = new ObjectMapper()
	            .writeValueAsString(resetPasswordDTO);
		
		Mockito.when(forgotPasswordService.resetPassword(Mockito.anyObject())).thenReturn(mockRestResponseDTO);
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				.post(RESET_PASSWORD_URL)
				.accept(MediaType.APPLICATION_JSON).content(expectedJsonString)
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mvc.perform(requestBuilder)
				.andExpect(status().isOk())
				.andExpect(jsonPath("status").value(AppConstant.REST_RESPONSE_SUCCESS))
				.andReturn();
		
	}
@Test
public void resetPasswordTokenValidationShouldReturnResponseDTO () throws Exception {
	mockRestResponseDTO  = new RestResponseDTO();
	mockRestResponseDTO.setStatus(AppConstant.REST_RESPONSE_SUCCESS);
	mockRestResponseDTO.setMessage("Your Token is Valid!");
	Mockito.when(forgotPasswordService.isResetPasswordLinkValid(Mockito.anyObject())).thenReturn(mockRestResponseDTO);
	RequestBuilder requestBuilder = MockMvcRequestBuilders
			.get(RESET_PASSWORD_TOKEN_VALIDATION_URL)
			.contentType(MediaType.APPLICATION_JSON);
	
	MvcResult result = mvc.perform(requestBuilder)
			.andExpect(status().isOk())
			.andExpect(jsonPath("status").value(AppConstant.REST_RESPONSE_SUCCESS))
			.andReturn();
	
}
}
