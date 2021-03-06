package com.knet51.courses.controllers.defs;

import java.util.HashMap;
import java.util.Map;

public final class GlobalDefs {
	public static final String SESSION_USER_INFO = "sessionUserInfo";
	public static final String COOKIE_IDENTITY = "identity";

	//	public static final Integer PUBLISH_NUM_DELETE = 0;//course is deleted,but in database, it still exit 
//	public static final Integer PUBLISH_NUM_RECYCLE = 1; //just show it in teacher recover page
//	public static final Integer PUBLISH_NUM_ADMIN= 2;//just show it in teacher admin page
//	public static final Integer PUBLISH_NUM_ADMIN_FRONT = 3;//show it in the admin page and front page
//	public static final Integer STATUS_CCWEB = 1; //just show the course in ccweb
//	public static final Integer STATUS_CCWEB_COURSES = 2;//both show
//	
//	
//	public static final Integer STATUS_COURSE_RESOURCE = 1;// show the resource in course
//	public static final Integer STATUS_RESOURCE = 2; //show the resource in teacher resource
	
	public static final Integer HOME_PATENT_COUNT = 2381435;
	public static final Integer HOME_TEACHER_COUNT = 8235;
	public static final Integer HOME_PATENT_TRADE_COUNT = 15487;
	public static final Integer HOME_PATENT_REQUIRE_COUNT = 23652; 
	public static final Integer HOME_PATENT_CN_COUNT = 2163580; 
	
	// the patent show status in home page
	public static final Integer HOME_FOCUS = 1;
	public static final Integer HOME_FOCUS_NOT = 0;
		
	// the patent belong to china or foreign
	public static final Integer PATENT_CHINA = 0;
	public static final Integer PATENT_FOREIGN = 1;
	
	public static final Integer STATUS_COURSE_RESOURCE = 1;// show the resource in course
	public static final Integer STATUS_RESOURCE = 2; //show the resource in teacher resource
	public static final Integer STATUS_RESOURCE_DESTORY = 3; //the resource has been deleted;
	
	public static final String STATUS_RESOURCETYPE = "rt";// just show the type in resource page
	public static final String STATUS_COURSE_RESOURCETYPE ="crt"; // just show the type in course resource page
	
	// pass or waite status
	public static final Integer WAITE = 0;
	public static final Integer PASS = 1;
	
	// complete or uncomplete
	public static final Integer COMPLETE = 1;
	public static final Integer UN_COMPLETE = 0;
	
	public static final String RZJG = "融资机构";
	public static final String FHYQ ="孵化园区";
	
	// default projects logo path
	public static final String PROJECTS_LOGO_PATH = "/resources/img/default/projects_logo.png";
	
	
	// fast upload default pwd
	public static final String DEFAULT_PWD = "000000";
	
	// fast upload default user avatar
	public static final String DEFAULT_PHOTO_URL = "/resources/img/avatar/p1.jpg";
	// register role
	public static final String USER_ROLE = "user";
	
	
	public static Map<String,String> getTechField(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "电子信息");
		map.put("2", "先进能源");
		map.put("3", "先进制造");
		map.put("4", "生物医药");
		map.put("5", "新材料");
		map.put("6", "现代交通");
		map.put("7", "资源环境");
		map.put("8", "现代农业");
		map.put("9", "海洋技术");
		map.put("10", "经济管理");
		map.put("11", "其他领域");
		return map;
	}
	
	public static Map<String, String> getProjectsField(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "互联网");
		map.put("2", "IT软件/硬件");
		map.put("3", "服务行业");
		map.put("4", "文化创意");
		map.put("5", "节能环保");
		map.put("6", "资源环境");
		map.put("7", "现代农业");
		map.put("8", "生物医药");
		map.put("9", "经济管理");
		map.put("10", "其他领域");
		return map;
	}
}
