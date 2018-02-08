package com.nisum.common.integration.user.dto;

/**
 * The Class UserDTO.
 */
public class UserDTO {

	/** The token. */
	private String authToken;

	/** The user id. */
	private Long id;
	
	private Long tokenValidity;
	
	private Long updatedAt;
	
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the new user id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public Long getTokenValidity() {
		return tokenValidity;
	}

	public void setTokenValidity(Long tokenValidity) {
		this.tokenValidity = tokenValidity;
	}

	public Long getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Long updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * @param authToken
	 * @param id
	 * @param tokenValidity
	 * @param updatedAt
	 */
	public UserDTO( Long id,String authToken, Long tokenValidity, Long updatedAt,String userName) {
		
		this.authToken = authToken;
		this.id = id;
		this.tokenValidity = tokenValidity;
		this.updatedAt = updatedAt;
		this.userName = userName;
	}

	public UserDTO() {
		
	}
	
}
