package com.nisum.user.service;

import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.user.dto.ProfileDTO;
import com.nisum.user.model.User;

import java.util.List;

/**
 * The Interface UserService.
 */
public interface UserService {
	
	/**
	 * Find one.
	 *
	 * @param id the id
	 * @return the user
	 */
	public User findOne(Long id);
	
	/**
	 * Save guest.
	 *
	 * @return the user
	 */
	public User saveGuest();
	
	
	/**
	 * Find all users.
	 *
	 * @return the list
	 */
	public List<User>findAllUsers();
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User createUser(User user);
	
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findUserByEmail(String email);
	
	/**
	 * Find user by email address and password.
	 *
	 * @param emailAddress the email address
	 * @param password the password
	 * @return the user
	 */
	public User findUserByEmailAddressAndPassword(String emailAddress, String password);
	
	/**
	 * Update user.
	 *
	 * @param user the user
	 * @return the user
	 */
	public User update(User user);
	
	/**
	 * Find active user by email.
	 *
	 * @param email the email
	 * @return the user
	 */
	public User findActiveUserByEmail(String email);
	
	/**
	 * Find user by reset token.
	 *
	 * @param resetToken the reset token
	 * @return the user
	 */
	public User findUserByResetToken(String resetToken);
	
	/**
	 * Find user by token and id.
	 *
	 * @param token the token
	 * @param id the id
	 * @return the user
	 */
	public User findUserByTokenAndId(String token, Long id);

	/**
	 * Checks if is alive.
	 *
	 * @param userId the user id
	 * @param accessToken the access token
	 * @return the boolean
	 */
	public Boolean isAlive(String userId, String accessToken);

	/**
	 * Updates the user profile
	 *
	 * @param ProfileDTO data transfer object of user profile
	 * @return the User
	 */
	public ProfileDTO updateProfile(ProfileDTO profileDTO, long userid) throws IllegalArgumentException;
	
	/**
	 * Gets the user profile DTO
	 *
	 * @param id the id
	 * @return the profile DTO
	 */
	public ProfileDTO getProfile(Long id);
	
	/**
	 * Finds the user by token received in request
	 * 
	 * @param token the token given to client during login or signup
	 * @return the user
	 */
	public UserDTO findUserByToken(String token);
	
}
