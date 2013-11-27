package com.knet51.courses.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyUtil {
	
	private MyUtil(){
		
	}
	
	private static MyUtil instance = null;
	public static MyUtil getInstance(){
		if(instance == null){
			instance = new MyUtil();
		}
		return instance;
	}
	
	public  String change(String src) {   
        if (src != null) {   
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }
	
	public  List findField(Object obj) throws Exception{
		List<Object> list = new ArrayList<Object>();
		Field[] f = obj.getClass().getDeclaredFields();
		
		for (Field field : f) {
			String fdName = field.getName();
			Method m = obj.getClass().getMethod("get"+change(fdName), null);
			Object o = m.invoke(obj, null);
			if(!o.equals("")){
				list.add(o);
			}
		}
		return list;
	}
	
	
	public String replaceSpace(String s){
		String reg = "\\s+";
		return s.replaceAll(reg, "%");
	}
	
}
