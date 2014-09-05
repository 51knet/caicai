package com.knet51.courses.util;

import java.io.FileInputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

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
	public static  String change(String src) {   
        if (src != null) {   
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
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
	
	public static String getPropertyValueByKey(String key,String path,HttpServletRequest request) throws Exception{
		String value = null;
		Properties p = new Properties();
		FileInputStream in = new FileInputStream(getContextPath(request)+path);
		p.load(in);
		value = p.getProperty(key);
		in.close();
		return value;
	}
	
	private static String getContextPath(HttpServletRequest request){
		return request.getSession().getServletContext().getRealPath("/");
	}
	
	
	public static void copyValidBeanToDestBean(Object orgin, Object dest){
		Field[] orginFields = orgin.getClass().getDeclaredFields();
		try {
			Field[] destFields = dest.getClass().getDeclaredFields();	
			for (Field orgField : orginFields) {
				for (Field destField : destFields) {
					if(destField.getName().equals(orgField.getName())){
						//System.out.println("---->destfieldname="+destField.getName()+"----- orginfieldname="+orgField.getName());
						Method destSetMethod = dest.getClass().getMethod("set"+changeFieldNameFirstUpper(destField.getName()), destField.getType());
						Method orginGetMethod = orgin.getClass().getMethod("get"+changeFieldNameFirstUpper(destField.getName()));
						destSetMethod.invoke(dest, orginGetMethod.invoke(orgin));
					}
				}
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public static  String changeFieldNameFirstUpper(String src) {   
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }
	
	public static String chengeDateToString(Date date){
		String dates = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm");
		dates = format.format(date);
		return dates;
	}
	
	public static void main(String[] args) {
	}
	
}
