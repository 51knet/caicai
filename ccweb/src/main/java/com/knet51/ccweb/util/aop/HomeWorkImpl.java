package com.knet51.ccweb.util.aop;

import org.springframework.stereotype.Component;

@Component
public class HomeWorkImpl implements HomeWork {
	
	
	public void showNames(String word){
		System.out.println("=== i say: "+word);
	}
}
