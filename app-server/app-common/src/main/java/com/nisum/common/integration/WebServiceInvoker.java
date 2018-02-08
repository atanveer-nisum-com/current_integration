/**
 * 
 */
package com.nisum.common.integration;

import com.nisum.common.integration.user.dto.UserDTO;

/**
 * @author Kahmed
 *
 */

public interface WebServiceInvoker {

	
	
	/**
	 * 
	 * @param token
	 * @return
	 */
	public UserDTO authorize(String token);
}
