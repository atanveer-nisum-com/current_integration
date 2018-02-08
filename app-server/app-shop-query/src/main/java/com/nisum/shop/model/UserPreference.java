package com.nisum.shop.model;

import java.io.Serializable;

/**
 * The Class UserPreference.
 */
public class UserPreference extends BaseModel implements Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	private Long id;
	
	/** The user. */
	//bi-directional many-to-one association to User
	private User user;
	
	/** The preference. */
	
	/** The value. */
	private Long value;
	
	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the preference.
	 *
	 * @return the preference
	 */

	/**
	 * Sets the preference.
	 *
	 * @param preference the new preference
	 */

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Long getValue() {
		return value;
	}

	/**
	 * Sets the value.
	 *
	 * @param value the new value
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	
	/* (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (id == null ? 0 : id.hashCode());
		return result;
	}

	
	 /** (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserPreference other = (UserPreference) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
