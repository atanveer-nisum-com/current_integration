package com.nisum.user.service.impl;

import com.nisum.common.constant.AppConstant;
import com.nisum.common.util.AESEncryptionUtils;
import com.nisum.common.util.DateUtils;
import com.nisum.user.dto.ForgotPasswordDTO;
import com.nisum.user.dto.ResetPasswordDTO;
import com.nisum.user.dto.RestResponseDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.EmailService;
import com.nisum.user.service.ForgotPasswordService;
import com.nisum.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * The Class ForgetPasswordImpl.
 */
@Service("forgotPasswordService")
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	/** The email service. */
	@Autowired
	private EmailService emailService;

	/** The user service. */
	@Autowired
	private UserService	 userService;

	/* (non-Javadoc)
	 * @see com.nisum.service.SignupService#forgetPassword(com.nisum.dto.ForgetPasswordDTO)
	 */
	@Override
	@Transactional
	public RestResponseDTO forgetPassword(ForgotPasswordDTO forgotPasswordDTO) {
		RestResponseDTO response = new RestResponseDTO();
		
		User user = userService.findActiveUserByEmail(forgotPasswordDTO.getEmail());
		
		if(user != null){
			String resetToken = UUID.randomUUID().toString();
			user.setResetToken(resetToken);
			user.setTokenCreatedAt(DateUtils.convertMillisIntoSeconds(System.currentTimeMillis()));
			userService.update(user);
			
			emailService.sendForgetPasswordMail(user, resetToken);
			
			response.setStatus(HttpStatus.OK.toString());
			response.setMessage("Check your email for a link to reset your password. If it doesn't appear within a few minutes, check your spam folder.");
			return response;
		}
		throw new EmptyResultDataAccessException(1);
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.SignupService#resetPassword(com.nisum.dto.ResetPasswordDTO)
	 */
	@Override
	@Transactional
	public RestResponseDTO resetPassword(ResetPasswordDTO resetPasswordDTO) {
		RestResponseDTO response;

		try {
			response = this.isResetPasswordLinkValid(resetPasswordDTO.getResetToken());

			if(!(resetPasswordDTO.getPassword().equals(resetPasswordDTO.getConfirmPassword()))){
				throw new IllegalArgumentException("Passwords does not match");
			}

			User user = userService.findUserByResetToken(resetPasswordDTO.getResetToken());

			if(user != null){
				user.setPassword(AESEncryptionUtils.encrypt(resetPasswordDTO.getPassword()));
				user.setResetToken(null);
				user.setTokenCreatedAt(null);
				userService.update(user);

				emailService.sendResetPasswordMail(user);

				response.setStatus(AppConstant.REST_RESPONSE_SUCCESS);
				response.setMessage("Your password has been reset!");
			return response;
			}
			throw new EmptyResultDataAccessException(1);
		} catch (InvalidDataAccessApiUsageException | EmptyResultDataAccessException e) {
			throw e;
		}
	}
	
	/* (non-Javadoc)
	 * @see com.nisum.service.SignupService#isResetPasswordLinkValid(java.lang.String)
	 */
	@Override
	public RestResponseDTO isResetPasswordLinkValid(String resetToken){
		RestResponseDTO response = new RestResponseDTO();
		
		User user = userService.findUserByResetToken(resetToken);
		
		if(user!=null){
			long timeIntervalInSeconds = DateUtils.convertDaysIntoSeconds(AppConstant.RESET_PWD_TOKEN_EXPIRY_TIME_IN_DAYS);
			long tokenCreateTimeInSeconds = user.getTokenCreatedAt(); 
			long currentTimeInSeconds = DateUtils.convertMillisIntoSeconds(System.currentTimeMillis());
			
			long timeElapsed = currentTimeInSeconds - tokenCreateTimeInSeconds;
			
			if(timeElapsed > timeIntervalInSeconds){
				throw new InvalidDataAccessApiUsageException("Token validity expired");
			}else{
				response.setStatus(AppConstant.REST_RESPONSE_SUCCESS);
				response.setMessage("Reset password link is valid");
			}
		return response;
		}
		throw new EmptyResultDataAccessException(1);
	}

}
