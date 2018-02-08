/**
 * 
 */
package com.nisum.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.nisum.common.integration.WebServiceInvoker;
import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.common.servlet.requestwrapper.ExtendedServletRequestWrapper;
import com.nisum.common.util.DateUtils;

/**
 * @author Kahmed
 *
 */
@Component
@Order(1)
@WebFilter("/*")
public class AuthorizationFilter implements Filter {
	
	private final Logger log = LoggerFactory.getLogger(AuthorizationFilter.class);

	@Autowired
	private WebServiceInvoker webServiceInvoker;
	
	public AuthorizationFilter() {
	}
	
	public AuthorizationFilter(WebServiceInvoker webServiceInvoker) {
		this.webServiceInvoker = webServiceInvoker;
	}

	@Override
	public void destroy() {

		log.debug("AuthorizationFilter --destroy method");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		log.debug("AuthorizationFilter --doFilter method");

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		String[] arr = httpRequest.getRequestURI().split("/");
		if (arr.length > 1 && arr[1].equalsIgnoreCase("authorize")) {
			filterChain.doFilter(httpRequest, httpResponse);
		} else {
			String token = httpRequest.getHeader("auth-token");
			if (null != token) {
					UserDTO userDto = webServiceInvoker.authorize(token);

				if (userDto == null || (userDto.getTokenValidity() != null && userDto.getTokenValidity()<=DateUtils.getCurrentTime())) {
					HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper (httpResponse);
					wrapper.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Un-Authorized or token expired");
					response = wrapper.getResponse();
					return;
				}
				filterChain.doFilter(addUserData(userDto,httpRequest) ,httpResponse);
				
			} else {
				filterChain.doFilter(addUserData(new UserDTO(),httpRequest), httpResponse);
			}
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
			log.debug("AuthorizationFilter --init method");
	}
	/**
	 * 
	 * @param userDTO
	 * @param httpRequest
	 * @return ExtendedServletRequestWrapper
	 */
	private ExtendedServletRequestWrapper addUserData(UserDTO userDTO ,HttpServletRequest httpRequest) {
		ExtendedServletRequestWrapper extendedServletRequestWrapper = 
				new ExtendedServletRequestWrapper(httpRequest);
		extendedServletRequestWrapper.addHeader("userId", String.valueOf(userDTO.getId()));
		extendedServletRequestWrapper.addHeader("userName", userDTO.getUserName());
		return extendedServletRequestWrapper;
	}

}
