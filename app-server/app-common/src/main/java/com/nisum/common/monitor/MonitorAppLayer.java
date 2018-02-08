package com.nisum.common.monitor;

import org.apache.catalina.connector.RequestFacade;
import org.apache.catalina.connector.ResponseFacade;
import org.apache.catalina.core.ApplicationFilterChain;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;


/**
 * This singleton class is for monitoring the time consumption in class level layers.
 * 
 **/
public class MonitorAppLayer {

	/** The Constant logger. */
	static final Logger logger = LoggerFactory.getLogger(MonitorAppLayer.class);

	/** The instance. */
	private static MonitorAppLayer instance = null;

	/**
	 * Instantiates a new monitor app layer.
	 */
	private MonitorAppLayer() {
		super();
	}

	/**
	 * Gets the single instance of MonitorAppLayer.
	 *
	 * @return single instance of MonitorAppLayer
	 */
	public static MonitorAppLayer getInstance() {
		if (instance == null) {
			instance = new MonitorAppLayer();
		}
		return instance;
	}

	/**
	 * Do trace.
	 *
	 * @param jp PointJoint
	 * @return other PointJoint
	 * @throws Throwable the throwable
	 */
	public Object doTrace(ProceedingJoinPoint jp) throws Throwable {
		logger.trace("Starts: " + jp.toShortString());
		return traceMethod(jp);
	}

	/**
	 * Trace method.
	 *
	 * @param jp PointJoint
	 * @return other PointJoint
	 * @throws Throwable the throwable
	 */
	private Object traceMethod(ProceedingJoinPoint jp) throws Throwable {
		StopWatch clock = new StopWatch(jp.toShortString());
		clock.start(jp.toShortString());
		try {
			traceArgs(jp);
			return jp.proceed();
		} catch (Throwable e) {
			logger.error("Error :" + e);
			throw e;
		} finally {
			clock.stop();
			logger.trace("End At :" + clock.prettyPrint());
		}
	}

	/**
	 * Tracing variables which are passing into methods.
	 * 
	 * @param jp - JoinPoint jp
	 **/
	private void traceArgs(ProceedingJoinPoint jp) {
		for (Object obj : jp.getArgs()) {
			logValues(obj);
		}
	}

	/**
	 * Log values.
	 *
	 * @param obj            Object which is passing into method.
	 */
	private void logValues(Object obj) {
		if (obj instanceof Long) {
			logger.trace(String.format("Long Value: %s", ((Long) obj).longValue()));
		} else if (obj instanceof RequestFacade) {
			logger.trace("URL: " + ((RequestFacade) obj).getRequestURL());
		} else if (obj instanceof ResponseFacade) {
			// Ignore
		} else if (obj instanceof ApplicationFilterChain) {
			// Ignore
		} else {
			logger.trace("Param: " + obj);
		}
	}
}