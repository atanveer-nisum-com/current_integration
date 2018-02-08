package com.nisum.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * <p>
 * A {@link Filter} that enable client-side cross-origin requests by
 * implementing W3C's CORS (<b>C</b>ross-<b>O</b>rigin <b>R</b>esource
 * <b>S</b>haring) specification for resources. Each {@link HttpServletRequest}
 * request is inspected as per specification, and appropriate response headers
 * are added to {@link HttpServletResponse}.
 * </p>
 * 
 * <p>
 * By default, it also sets following request attributes, that helps to
 * determine nature of request downstream.
 * <ul>
 * <li> Flag to determine if request is a CORS
 * request. Set to <code>true</code> if CORS request; <code>false</code>
 * otherwise.</li>
 * <li>The Origin URL.</li>
 * <li>Request headers sent as
 * 'Access-Control-Request-Headers' header, for pre-flight request.</li>
 * </ul>
 * </p>
 * 
 * 
 * @see <a href="http://www.w3.org/TR/cors/">CORS specification</a>
 * 
 */
@Component
@Order(2)
public class CORSFilter implements Filter {

	/** {@link Logger} for {@link CORSFilter}	 **/
	private final Logger log = LoggerFactory.getLogger(CORSFilter.class);

	@Override
	public void destroy() {
		log.info("CORSFilter -- destroy");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (request.getRequestURI().contains("inform")) return ;
		
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me, auth-token, X-User-ID, X-User-Alive");
		response.setHeader("Access-Control-Expose-Headers", "X-User-Alive");
		
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		log.info("CORSFilter -- init");
	}

}
