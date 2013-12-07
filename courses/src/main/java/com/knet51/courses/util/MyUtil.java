package com.knet51.courses.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class MyUtil {
	
//	private MyUtil(){
//		
//	}
//	
//	private static MyUtil instance = null;
//	public static MyUtil getInstance(){
//		if(instance == null){
//			instance = new MyUtil();
//		}
//		return instance;
//	}
	
//	public MyUtil(){
//		
//	}
	
	public static  String change(String src) {   
        if (src != null) {   
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }
	
	public static  Map<String,String> findPostUnnullInput(Object obj) throws Exception{
		Map<String,String> map = new HashMap<String, String>();
		Field[] f = obj.getClass().getDeclaredFields();
		
		for (Field field : f) {
			String fdName = field.getName();
			Method m = obj.getClass().getMethod("get"+change(fdName), null);
			String o = (String) m.invoke(obj, null);
			if(!o.trim().equals("") && o != null){
				map.put(fdName, o);
			}
		}
		return map;
	}
	
	
	public static String replaceSpace(String s){
		String reg = "\\s+";
		if(s.trim().equals("") || s == null){
			s = "%";
			//System.out.println("the s is null or  '' "+s);
			return s.trim();
		}else{
			//System.out.println("the s is replace "+s.replaceAll(reg, "%"));
			return s.replaceAll(reg, "%");
			
		}
		
	}
	
	public static String getPropertyValueByKey(String key,String path) throws Exception{
		String value = null;
		Properties p = new Properties();
		FileInputStream in = new FileInputStream(path);
		p.load(in);
		value = p.getProperty(key);
		return value;
	}
	
	public static void main(String[] args) {
		String path = "src/main/resources/knet_url.properties";
		try {
			System.out.println(getPropertyValueByKey("knet_url", path));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		};
	}
	
}
