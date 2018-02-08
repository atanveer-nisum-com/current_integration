package com.nisum.user.controller;

import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.SignUpFormDTO;
import com.nisum.user.dto.TokenDTO;
import com.nisum.user.model.User;
import com.nisum.user.service.EmailService;
import com.nisum.user.service.UserService;
import com.nisum.user.util.UserConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.util.concurrent.ExecutorService;

/**
 * The Class SignupController.
 */
@RestController
@RequestMapping("users/signup")
public class SignupController extends BaseController {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(SignupController.class);

	/** The nisum service. */
	@Autowired
	private UserService userService;

	/** The email service. */
	@Autowired
	private EmailService emailService;	


	@Autowired
	private ExecutorService executorService;

	@Autowired
	private UserConverter converter;

	@PostMapping
	public DeferredResult<TokenDTO> createUser(@RequestBody SignUpFormDTO signupForm){
		
		DeferredResult<TokenDTO> signupResult = new DeferredResult<>();
		executorService.execute(() -> {
//			User convertedUser = ETDUtils.toUser(signupForm);
			User convertedUser = converter.convertToEntity(signupForm);
			User createdUser;
			try {
				createdUser = userService.createUser(convertedUser);				
				if (signupForm.getGuestId() != null) {
					
					userService.update(createdUser);
					emailService.sendSignupEmail(createdUser);
				}
				TokenDTO token = new TokenDTO();
				token.setToken(createdUser.getAuthToken());
				signupResult.setResult(token);
			} catch(InvalidDataAccessApiUsageException e) {
				signupResult.setErrorResult(new RestException(HttpStatus.UNPROCESSABLE_ENTITY, "Invalid zipcode",
						ErrorLevel.ERROR));
			} catch (DuplicateKeyException e) {
				throw new RestException(HttpStatus.CONFLICT, "Email exists", ErrorLevel.ERROR);
			}
		});
		return signupResult;
	}
	

}
