package com.knet51.ccweb.util.aop;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class HomeWorkAfterAdvice implements AfterReturningAdvice {

	
	public void afterReturning(Object arg0, Method arg1, Object[] arg2,
			Object arg3) throws Throwable {
		System.out.println("after=====");
	}

}
