package com.knet51.ccweb.util.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.stereotype.Component;
@Component
public class HomeWorkRoundAdvice implements MethodInterceptor {

	
	public Object invoke(MethodInvocation arg0) throws Throwable {
		before();
		Object o = arg0.proceed();
		after();
		return o;
	}
	
	public static void before(){
		System.out.println("before=====");
	}
	public static void after(){
		System.out.println("after======");
	}

}
