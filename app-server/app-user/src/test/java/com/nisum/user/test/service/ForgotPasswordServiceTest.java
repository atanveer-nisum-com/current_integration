package com.nisum.user.test.service;

import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.ForgotPasswordDTO;
import com.nisum.user.dto.ResetPasswordDTO;
import com.nisum.user.dto.RestResponseDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.ForgotPasswordService;
import com.nisum.user.service.UserService;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyString;

public class ForgotPasswordServiceTest extends BaseServiceTest {

	/** The user service. */
	
	@Autowired
	private ForgotPasswordService forgotPasswordService;

	@MockBean
	private UserService userService;

	/** Should Retrieve Forgot password*/
	@Test
	public void shouldRetrieveForgotPassword() {
		Mockito.when(userService.findActiveUserByEmail(createResetPasswordDtoMock().getResetToken())).thenReturn(createUserMock());
		Mockito.when(userService.update(createUserMock())).thenReturn(createUserMock());
		
		RestResponseDTO restResponseDto=forgotPasswordService.forgetPassword(new ForgotPasswordDTO());
		assertNotNull(restResponseDto);
	}
	@Test
	public void shouldReturnResponseWhenUserIsNull() {
		Mockito.when(userService.findActiveUserByEmail(createResetPasswordDtoMock().getResetToken())).thenReturn(null);
		
		RestResponseDTO restResponseDto=forgotPasswordService.forgetPassword(new ForgotPasswordDTO());
		assertNotNull(restResponseDto);
	}
	/** Should Reset Password */
	@Test
	public void shouldResetPassword() {
		
//		Mockito.when(forgetPasswordService.isResetPasswordLinkValid(anyString())).thenReturn(new RestResponseDTO());
		Mockito.when(userService.findUserByResetToken(Mockito.anyString())).thenReturn(createUserMock());
		Mockito.when(userService.update(createUserMock())).thenReturn(new User());
		Mockito.when(userService.findUserByResetToken(anyString())).thenReturn( createUserMock());
		RestResponseDTO restResponseDto=forgotPasswordService.resetPassword( createResetPasswordDTOForResetMock());
		assertNotNull(restResponseDto);
	}
	
	@Test
	public void shouldReturnResponseIfPasswordsAreNotEqual() {
		ResetPasswordDTO resetpasswordDTO=new ResetPasswordDTO();
		resetpasswordDTO.setPassword("waseem123");
	resetpasswordDTO.setConfirmPassword("waseem");
		resetpasswordDTO.setResetToken("asasasbn123");
	
//		Mockito.when(forgetPasswordService.isResetPasswordLinkValid(anyString())).thenReturn(new RestResponseDTO());
		Mockito.when(userService.findUserByResetToken(Mockito.anyString())).thenReturn(createUserMock());
		Mockito.when(userService.update(createUserMock())).thenReturn(new User());
		Mockito.when(userService.findUserByResetToken(anyString())).thenReturn( createUserMock());
		RestResponseDTO restResponseDto=forgotPasswordService.resetPassword( resetpasswordDTO);
		assertNotNull(restResponseDto);
	}
	@Test
	public void shouldReturnResponseIfPasswordsAreEqualButUserNull() {
	
//		Mockito.when(forgetPasswordService.isResetPasswordLinkValid(anyString())).thenReturn(new RestResponseDTO());
		Mockito.when(userService.findUserByResetToken(Mockito.anyString())).thenReturn(createUserMock());
		Mockito.when(userService.update(createUserMock())).thenReturn(new User());
		Mockito.when(userService.findUserByResetToken(anyString())).thenReturn( null);
		RestResponseDTO restResponseDto=forgotPasswordService.resetPassword( createResetPasswordDTOForResetMock());
		assertNotNull(restResponseDto);
	}
	/** Should check is Reset password*/
	@Test
	public void shouldCheckIfResetPasswordLinkValid() {
		Mockito.when(userService.findUserByResetToken(Mockito.anyString())).thenReturn(createUserMock());
		RestResponseDTO restResponseDto=forgotPasswordService.isResetPasswordLinkValid(Mockito.anyString());
		assertNotNull(restResponseDto);
	}
	

	/** Create User Mock
	 * @return User*/
	private User createUserMock() {
		
		User user=new User();
		user.setPassword("dfds");
		user.setFirstName("waseem");
		user.setLastName("saeed");
		user.setPassword("waseem123");
		user.setTokenCreatedAt(DateUtils.getCurrentTime());
		return user;
	}
	/** Create Reset Password dto for Mock
	 * @return ResetPasswordDTO */
	private ResetPasswordDTO createResetPasswordDtoMock() {
		ResetPasswordDTO resetpasswordDto=new ResetPasswordDTO();

		return resetpasswordDto;
	}
	/** Create ResetPasswordDTO for mock
	 * @return ResetPasswordDTO*/
	private ResetPasswordDTO createResetPasswordDTOForResetMock() {
		ResetPasswordDTO resetpasswordDTO=new ResetPasswordDTO();
		resetpasswordDTO.setPassword("waseem123");
	resetpasswordDTO.setConfirmPassword("waseem123");
		resetpasswordDTO.setResetToken("asasasbn123");
		
		return resetpasswordDTO;
	}
}
