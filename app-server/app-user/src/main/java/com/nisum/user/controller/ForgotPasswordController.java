package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.ForgotPasswordDTO;
import com.nisum.user.dto.ResetPasswordDTO;
import com.nisum.user.dto.RestResponseDTO;
import com.nisum.user.service.ForgotPasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class ForgotPasswordController extends BaseController {
	
	@Autowired
	ForgotPasswordService forgotPasswordService;
	
	/**
	 * Forget password.
	 *
	 * @param forgotPasswordDTO the forget password DTO
	 * @return the rest response DTO
	 */
	@PostMapping("/forgetpassword")
	public RestResponseDTO forgetPassword(@RequestBody ForgotPasswordDTO forgotPasswordDTO) {
		try {
			return forgotPasswordService.forgetPassword(forgotPasswordDTO);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No user found", ErrorLevel.ERROR);
		}
	}

	/**
	 * Reset password.
	 *
	 * @param resetToken the reset token
	 * @return the rest response DTO
	 */
	@GetMapping("/resetpassword/{resetToken}")
	public RestResponseDTO resetPassword(@PathVariable String resetToken) {
		try {
			return forgotPasswordService.isResetPasswordLinkValid(resetToken);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid reset password link", ErrorLevel.ERROR);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new RestException(HttpStatus.UNAUTHORIZED, "Link validity expired", ErrorLevel.ERROR);
		}
	}

	/**
	 * Reset password.
	 *
	 * @param resetPasswordDTO the reset password DTO
	 * @return the rest response DTO
	 */
	@PostMapping("/resetpassword")
	public RestResponseDTO resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO) {
		try {
			return forgotPasswordService.resetPassword(resetPasswordDTO);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid reset password link", ErrorLevel.ERROR);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Passwords not match", ErrorLevel.ERROR);
		}
	}
	

}
