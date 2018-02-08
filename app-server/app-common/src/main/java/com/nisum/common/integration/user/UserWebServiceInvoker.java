/**
 * 
 */
package com.nisum.common.integration.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nisum.common.constant.CommonEndPointConstant;

import com.nisum.common.integration.WebServiceInvoker;
import com.nisum.common.integration.user.dto.UserDTO;


/**
 * @author Kahmed
 *
 */
@Service("userwebserviceinvoker")
public class UserWebServiceInvoker implements WebServiceInvoker {
	
	
	private final RestTemplate restTemplate;
//	
	
	
	/* (non-Javadoc)
	 * @see com.nisum.common.integration.WebServiceInvoker#authorize(java.lang.String)
	 */
	@Override
	public UserDTO authorize(String token) {
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
		headers.add("auth-token", token );
		//String url = appUserConfig.getPropertyValueByName("appuser.webservice.rest.base.url")
		//		+ AppUserRestEndPoint.GetUserAuthorizationTokenURL.getEndPoint();
		
		String url = CommonEndPointConstant.APP_USER_BASE_URL
				+ CommonEndPointConstant.AUTHORIZE_TOKEN; //AppUserRestEndPoint.GetUserAuthorizationTokenURL.getEndPoint();
		
		HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
		ResponseEntity<UserDTO> response =  restTemplate.exchange(url, HttpMethod.POST, entity, UserDTO.class);
		return response.getBody();
	}
	public UserWebServiceInvoker (RestTemplateBuilder builder) {
	   restTemplate = builder.build();
	}

}
