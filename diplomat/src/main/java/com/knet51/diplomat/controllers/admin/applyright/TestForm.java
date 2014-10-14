package com.knet51.diplomat.controllers.admin.applyright;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.applyright.ApplyRight;

public class TestForm {
	public void showFormInfo(Object o) {
		try {
			Field[] f = o.getClass().getDeclaredFields();
			for (Field field : f) {
				Method m = o.getClass().getMethod("get"+changeFieldNameFirstUpper(field.getName()));
				System.out.println("obj get method="+m.getName());
				System.out.println(m.invoke(o, null));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void createTest(Object orgin, Object dest){
		Field[] orginFields = orgin.getClass().getDeclaredFields();
		try {
			Field[] destFields = dest.getClass().getDeclaredFields();	
			for (Field orgField : orginFields) {
				for (Field destField : destFields) {
					if(destField.getName().equals(orgField.getName())){
						System.out.println("---->destfieldname="+destField.getName()+"----- orginfieldname="+orgField.getName());
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
        	System.out.println("---->field name= "+src);
            StringBuffer sb = new StringBuffer(src);   
            sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));   
            return sb.toString();   
        } else {   
            return null;   
        }   
    }
	
	public static void main(String[] args) {
//		PersonApplyRightForm form = new PersonApplyRightForm();
//		form.setContent("ApplyRightFormApplyRightForm");
//		form.setIdNum("1");
//		form.setName("ApplyRightForm");
//		form.setPhone("123");
//		
//		ApplyRight applyRight = new ApplyRight();
//		new TestForm().createTest(form, applyRight);
//		System.out.println(applyRight.getContent());
		
//		try {
//			ApplicationContext context = new ClassPathXmlApplicationContext("root-context.xml"); 
//			BaseDao<User> dao = (BaseDao) context.getBean("baseDao");
//			User user = new User();
//			user.setName("testbasedao");
//			user.setEmail("testbasedao");
//			dao.create(user);
//			System.out.println("------------ fish");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
}
