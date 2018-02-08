package com.nisum.user.repository;

import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * The Interface UserRepository.
 */
@Repository
public interface UserRepository  extends JpaRepository<User, Long>{
	
	/* (non-Javadoc)
	 * @see org.springframework.data.jpa.repository.JpaRepository#findAll()
	 */
	public List<User> findAll();
	
	
	@Query("SELECT e FROM User e WHERE e.id=?1 and e.authToken=?2 and e.tokenValidity >=?3 ")
	public User findOne(Long id, String accessToken, Long tokenCreatedAt);
	/**
	 * Find user by email.
	 *
	 * @param email the email
	 * @return the list
	 */
	@Query("SELECT e FROM User e  WHERE e.emailAddress=?1")
	public List<User> findUserByEmail(String email);
	
	/**
	 * Find user by email.
	 *
	 * @param emailAddress the email address
	 * @param password the password
	 * @return the list
	 */
	@Query("SELECT e FROM User e  WHERE e.emailAddress=?1 and e.password=?2 and e.isDeleted = 0")
	public User findUserByEmailAddressAndPassword(String emailAddress, String password);

	/**
	 * Find user by id.
	 *
	 * @param id the id
	 * @return the user
	 */
	@Query("SELECT distinct u FROM User u WHERE u.id = ?1 AND u.isDeleted = 0")
	public User findUserById(Long id);
	
	/**
	 * Find active user by email.
	 *
	 * @param email the email
	 * @return the list
	 */
	@Query("SELECT distinct u FROM User u WHERE u.emailAddress=?1 AND u.isDeleted = 0")
	public List<User> findActiveUserByEmail(String email);

	/**
	 * Find user by reset token.
	 *
	 * @param resetToken the reset token
	 * @return the list
	 */
	@Query("SELECT u FROM User u WHERE u.resetToken=?1")
	public List<User> findUserByResetToken(String resetToken);


	@Query("SELECT u FROM User u WHERE u.authToken=?1 AND u.id=?2 AND u.isDeleted = 0")
	public List<User> findUserByTokenAndId(String token, Long id);

	/**
	 * Find user by token.
	 * 
	 * @param token the token generated during signup or login and was given to the client.
	 * @return the user
	 */
	@Query("SELECT new com.nisum.common.integration.user.dto.UserDTO(u.id,u.authToken,u.tokenValidity,u.updatedAt,u.emailAddress) FROM User u WHERE u.authToken=?1 AND u.isDeleted = 0")
	public UserDTO findUserByToken(String token);
}
