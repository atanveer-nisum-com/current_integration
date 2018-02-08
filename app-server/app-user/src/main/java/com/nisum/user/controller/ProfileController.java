/**
 * 
 */
package com.nisum.user.controller;
import com.nisum.common.constant.ErrorLevel;
import com.nisum.common.exception.rest.RestException;
import com.nisum.user.dto.ProfileDTO;
import com.nisum.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author mabdullah
 *
 */
@RestController
@RequestMapping("users/profile")
public class ProfileController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(ProfileController.class);
	/** The user service. */
	@Autowired
	private UserService userService;
	
	/**
	 * Gets the User Profile.
	 *
	 * @param userId the user id
	 * @return the User
	 */
	@GetMapping()
	public ProfileDTO getProfile(@RequestHeader(value = "userId", required = true)Long userId) {
		try {
			return userService.getProfile(userId);
		} catch (EmptyResultDataAccessException e) {
			throw new RestException(HttpStatus.NOT_FOUND, "No user found", ErrorLevel.ERROR);
		}
	}
	
	@PutMapping()
	public ProfileDTO updateProfile(@RequestBody ProfileDTO profileDTO, @RequestHeader(value = "userId", required = true) Long userId) {
		try {
			return userService.updateProfile(profileDTO,userId);
		} catch (DuplicateKeyException e) {
			throw new RestException(HttpStatus.CONFLICT, e.getLocalizedMessage(), ErrorLevel.ERROR);
		} catch (InvalidPropertyException | IllegalArgumentException e) {
			throw new RestException(HttpStatus.UNPROCESSABLE_ENTITY, e.getLocalizedMessage(), ErrorLevel.ERROR);
		}
	}
	
}
