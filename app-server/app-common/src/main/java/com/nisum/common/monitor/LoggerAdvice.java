package com.nisum.common.monitor;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;



/**
 * This advice class is used to trace layers.
 * 
 **/
@Aspect
@Component
public class LoggerAdvice {

	/**
	 * Around controller log.
	 *
	 * @param jp the jp
	 * @return the object
	 * @throws Throwable the throwable
	 */
	@Around("execution(* com.nisum..*.*(..))")
	public Object aroundControllerLog(ProceedingJoinPoint jp) throws Throwable {
		return MonitorAppLayer.getInstance().doTrace(jp);
	}

}
