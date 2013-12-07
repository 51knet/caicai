package com.knet51.courses.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

public class Test {
	private static Properties SetProperties() throws Exception{
		Properties p = new Properties();
		FileInputStream in  = new FileInputStream("src/main/resources/test.properties");
		p.load(in);
		return p;
	}
	
	public static void LoadProperties() throws Exception{
		Properties p = SetProperties();
		System.out.println(p.getProperty("id"));
		System.out.println(p.getProperty("value"));
	}
	
	public static void main(String[] args) {
		try {
			LoadProperties();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
