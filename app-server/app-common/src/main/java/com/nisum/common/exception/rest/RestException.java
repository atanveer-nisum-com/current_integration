package com.nisum.common.exception.rest;

import org.springframework.core.NestedRuntimeException;
import org.springframework.http.HttpStatus;

import com.nisum.common.constant.ErrorLevel;



/**
 * Base class for exceptions thrown by {@link BaseController} whenever it
 * encounters errors.This class for wrapping runtime {@code Exceptions} with a
 * root cause.
 *
 * <p>
 * The similarity between this class and the {@link NestedRuntimeException}
 * class is unavoidable, as Java forces these two classes to have different
 * superclass (ah, the inflexibility of concrete inheritance!).
 *</p>
 * 
 * @author Jibran.
 */
public class RestException extends NestedRuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 5616278716290119819L;

	/** The http status. */
	private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

	/** The error level. */
	private ErrorLevel errorLevel = ErrorLevel.INFO;

	/**
	 * Gets the http status.
	 *
	 * @return the http status
	 */
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ErrorLevel getErrorLevel() {
		return errorLevel;
	}

	/**
	 * Instantiates a new rest exception.
	 *
	 * @param httpStatus the http status
	 * @param message the message
	 * @param errorLevel the error level
	 */
	public RestException(HttpStatus httpStatus, String message, ErrorLevel errorLevel) {
		super(message);
		this.httpStatus = httpStatus;
		this.errorLevel = errorLevel;
	}

}
