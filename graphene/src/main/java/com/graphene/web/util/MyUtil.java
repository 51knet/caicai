package com.graphene.web.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;

import javax.servlet.ServletContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.graphene.web.common.defs.GlobalDefs;
import com.graphene.web.jpa.entity.require.TechRequirement;
import com.graphene.web.jpa.entity.user.User;
import com.graphene.web.service.UserService;
import com.graphene.web.service.requirement.TechRequireService;

public class MyUtil {
		
	public static String replaceSpaceWithPercent(String s){
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
	
	public static int getPageNumber(int pageNum, int listCount ,int pageSize){
		int maxPage = listCount % pageSize == 0? listCount/pageSize-1 : listCount/pageSize ;
		//int res1 = listCount/pageSize;
		//int res2 = listCount/pageSize-1;
		//System.out.println("======="+maxPage+"========="+res1+"-------"+res2+"---"+listCount);
		if(pageNum <=0){
			return 0;
		}else if(pageNum >= maxPage){
			return maxPage;
		}
		return pageNum;
	}
	
	public static String replaceTheSpace(String s){
		String reg = "\\s+";
		return s.trim().replaceAll(reg, "%");
	}
	
	
	public static void copyValidBeanToDestBean(Object orgin, Object dest){
		Field[] orginFields = orgin.getClass().getDeclaredFields();
		try {
			Field[] destFields = dest.getClass().getDeclaredFields();	
			for (Field orgField : orginFields) {
				for (Field destField : destFields) {
					if(destField.getName().equals(orgField.getName())){
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
	
	
	private static  String changeFieldNameFirstUpper(String src) {   
        if (src != null) {
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }
	
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml");
		TechRequireService service = (TechRequireService) context.getBean("techRequireService");
		UserService userService = (UserService) context.getBean("userService");
		User user = userService.findByEmailAddress("tim@apple.com");
	}
	
}
