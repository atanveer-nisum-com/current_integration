package com.nisum.common.filter.test;

import static org.mockito.Mockito.mock;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.junit.Test;
import org.mockito.Mockito;

import com.nisum.common.filter.AuthorizationFilter;
import com.nisum.common.integration.WebServiceInvoker;
import com.nisum.common.integration.user.dto.UserDTO;
import com.nisum.common.util.DateUtils;

public class AuthorizationFilterTest {

	
	@Test
	public void doFilterTestWithToken() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		final HttpServletRequestWrapper wrapped = new HttpServletRequestWrapper(request) {
            @Override
            public String getRequestURI() {
                return new String("localhost:8082/authorize/token");
            }
        };
        
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		FilterChain filterChain = mock(FilterChain.class);
		
		AuthorizationFilter filter = new AuthorizationFilter();
		
		filter.doFilter(wrapped, response, filterChain);
		
	}
	
	@Test
	public void doFilterTestWithoutToken() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		final HttpServletRequestWrapper wrapped = new HttpServletRequestWrapper(request) {
			
			private Map<String, String> headerMap = new HashMap<String, String>();
			
            @Override
            public String getRequestURI() {
                return new String("localhost:8082/users/addresses");
            }

        	@Override
        	public String getHeader(String name) {
        		headerMap.put("auth-token", "nfh2123");
        		return headerMap.toString();
        	}
        };
        
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		FilterChain filterChain = mock(FilterChain.class);
		
		WebServiceInvoker webServiceInvoker = mock(WebServiceInvoker.class);
		System.out.println(DateUtils.getCurrentTime());
		UserDTO userDTO = new UserDTO();
		userDTO.setId(1l);
		Mockito.when(webServiceInvoker.authorize(Mockito.anyString())).thenReturn(userDTO);
		
		Filter filter = new AuthorizationFilter(webServiceInvoker);
		
		filter.doFilter(wrapped, response, filterChain);
		
	}
	
	
	@Test
	public void doFilterTestWithTokenNull() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		final HttpServletRequestWrapper wrapped = new HttpServletRequestWrapper(request) {
			
			private Map<String, String> headerMap = new HashMap<String, String>();
			
            @Override
            public String getRequestURI() {
                return new String("localhost:8082");
            }

        	@Override
        	public String getHeader(String name) {
        		headerMap.put("auth-token", null);
        		return headerMap.get("auth-token");
        	}
        };
        
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		FilterChain filterChain = mock(FilterChain.class);
		
		WebServiceInvoker webServiceInvoker = mock(WebServiceInvoker.class);
		
		Filter filter = new AuthorizationFilter(webServiceInvoker);
		
		filter.doFilter(wrapped, response, filterChain);
		
	}
	
	@Test
	public void doFilterTestUnauthorizedUser() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);
		
		final HttpServletRequestWrapper wrapped = new HttpServletRequestWrapper(request) {
			
			private Map<String, String> headerMap = new HashMap<String, String>();
			
            @Override
            public String getRequestURI() {
                return new String("localhost:8082/users/addresses");
            }

        	@Override
        	public String getHeader(String name) {
        		headerMap.put("auth-token", "nfh2123");
        		return headerMap.toString();
        	}
        };
        
		HttpServletResponse response = mock(HttpServletResponse.class);
		
		FilterChain filterChain = mock(FilterChain.class);
		
		WebServiceInvoker webServiceInvoker = mock(WebServiceInvoker.class);
		
		Mockito.when(webServiceInvoker.authorize(Mockito.anyString())).thenReturn(null);
		
		Filter filter = new AuthorizationFilter(webServiceInvoker);
		
		filter.doFilter(wrapped, response, filterChain);
		
	}

}
