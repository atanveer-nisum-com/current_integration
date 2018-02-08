package com.nisum.user.service;

import com.nisum.user.dto.ForgotPasswordDTO;
import com.nisum.user.dto.ResetPasswordDTO;
import com.nisum.user.dto.RestResponseDTO;

public interface ForgotPasswordService {
	
	public RestResponseDTO forgetPassword(ForgotPasswordDTO forgotPasswordDTO);
	
	public RestResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO) throws IllegalArgumentException;
	
	public RestResponseDTO isResetPasswordLinkValid(String resetToken);

}
