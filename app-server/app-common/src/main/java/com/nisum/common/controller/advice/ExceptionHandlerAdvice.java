package com.nisum.common.controller.advice;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nisum.common.exception.rest.RestException;


/**
 * Allows customizing the response after the execution of an
 * {@code @ResponseBody} or a {@code ResponseEntity} controller advice method
 * but before the body is written with an {@code HttpMessageConverter}.
 *
 * <p>
 * Implementations may be registered directly with
 * {@code RequestMappingHandlerAdapter} and
 * {@code ExceptionHandlerExceptionResolver} in which case they will be
 * auto-detected by both.
 * </p>
 */
@RestControllerAdvice
public class ExceptionHandlerAdvice {

	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	/**
	 * Handle rest exception.
	 *
	 * @param exception the exception
	 * @return the response entity
	 */
	@ExceptionHandler(RestException.class)
	public ResponseEntity<Map<String,String>> handleRestException(RestException exception) {

		logger.error(String.format("Status: %s -> Message: %s", exception.getHttpStatus(), exception.getMessage()));

		return ResponseEntity.status(exception.getHttpStatus()).body(errorJson(exception.getHttpStatus(), exception.getMessage()));
	}

	/**
	 * Error json.
	 *
	 * @param message the message
	 * @return the map
	 */
	private Map<String,String> errorJson(HttpStatus errorCode, String message) {
		Map<String,String> result = new HashMap<String,String>();
		result.put("errorCode", errorCode.toString());
		result.put("errorDescription", message);
		return result;
	}

}
