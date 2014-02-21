package com.knet51.patents.util.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class SystemArchitecture {
	private static final Logger logger = LoggerFactory
			.getLogger(SystemArchitecture.class);
	/**
	   * A join point is in the web layer if the method is defined
	   * in a type in the com.xyz.someapp.web package or any sub-package
	   * under that.
	   */
	  @Pointcut("within(com.knet51.patents.controllers..*)")
	  public void inWebLayer() {}

	  /**
	   * A join point is in the service layer if the method is defined
	   * in a type in the com.xyz.someapp.service package or any sub-package
	   * under that.
	   */
	  @Pointcut("within(com.knet51.patents.jpa.service..*)")
	  public void inServiceLayer() {}

	  /**
	   * A join point is in the data access layer if the method is defined
	   * in a type in the com.xyz.someapp.dao package or any sub-package
	   * under that.
	   */
	  @Pointcut("within(com.knet51.ccweb.jpa.dao..*)")
	  public void inDataAccessLayer() {} 

	  /**
	   * A business service is the execution of any method defined on a service
	   * interface. This definition assumes that interfaces are placed in the
	   * "service" package, and that implementation types are in sub-packages.
	   * 
	   * If you group service interfaces by functional area (for example, 
	   * in packages com.xyz.someapp.abc.service and com.xyz.def.service) then
	   * the pointcut expression "execution(* com.xyz.someapp..service.*.*(..))"
	   * could be used instead.
	   *
	   * Alternatively, you can write the expression using the 'bean'
	   * PCD, like so "bean(*Service)". (This assumes that you have
	   * named your Spring service beans in a consistent fashion.)
	   */
	  @Pointcut("execution(* com.knet51.patents.jpa.services.*.*(..))")
	  public void businessService() {}
	  
	  /**
	   * A data access operation is the execution of any method defined on a 
	   * dao interface. This definition assumes that interfaces are placed in the
	   * "dao" package, and that implementation types are in sub-packages.
	   */
	  @Pointcut("execution(* com.knet51.ccweb.jpa.dao.*.*(..))")
	  public void dataAccessOperation() {
		  logger.info("ARRIVED HERE");
	  }
	  @Before("dataAccessOperation()") 
	  public void beforeDataAccessOperation() {
		  logger.info("beforeDataAccessOperation");
	  }
	  
	  @AfterThrowing(pointcut="com.knet51.patents.util.aop.SystemArchitecture.dataAccessOperation()",
			  throwing="ex")
	  public void doRecoveryActions(JoinPoint jp, DataAccessException ex) {
	    logger.info(jp.toString()+"\n"+ex.toString());
	  }
	 
}
