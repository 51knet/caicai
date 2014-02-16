package com.knet51.ccweb.util.aop;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class TestCGLib implements MethodInterceptor {
	
	private static TestCGLib instance = new TestCGLib();
	private TestCGLib(){};
	public static TestCGLib getinstance(){
		return instance;
	}
	
	@SuppressWarnings("unchecked")
	public <T>T getProxy(Class<T> clazz){
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return (T) enhancer.create();
	}
	
	
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println(method.getName());
		before();
		Object o = proxy.invokeSuper(obj, args);
		after();
		return o;
	}

	public static void before(){
		System.out.println("before=====");
	}
	public static void after(){
		System.out.println("after======");
	}

	public static void main(String[] args) {
//		Homework homework = TestCGLib.getinstance().getProxy(Homework.class);
//		homework.showNames("hiahiahiahia");
		
//		ProxyFactory factory = new ProxyFactory();
//		factory.setTarget(new HomeworkImpl());
//		factory.addAdvice(new HomeWorkBeforeAdvice());
//		factory.addAdvice(new HomeWorkAfterAdvice());
//		HomeWork work = (HomeWork) factory.getProxy();
//		work.showNames("hiahiahia");
		
//		factory.addAdvice(new HomeWorkRoundAdvice());
//		HomeWork work = (HomeWork) factory.getProxy();
//		work.showNames("hiahiahia");
		
		ApplicationContext context = new ClassPathXmlApplicationContext("servlet-context.xml");
		HomeWork work = (HomeWork) context.getBean("homeWorkProxy");
		work.showNames("hiahiahia");
	}
	

}
