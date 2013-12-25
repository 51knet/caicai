package com.knet51.patents.controllers.common.defs;

import java.util.HashMap;
import java.util.Map;

public final class GlobalDefs {
	public static final String SESSION_USER_INFO = "sessionUserInfo";
	public static final String COOKIE_IDENTITY = "identity";
	
	public static final Integer PUBLISH_NUM_DELETE = 0;//course is deled,but in database, it still exit
	public static final Integer PUBLISH_NUM_RECYCLE = 1; //just show it in teacher recover page
	public static final Integer PUBLISH_NUM_ADMIN= 2;//just show it in teacher admin page
	public static final Integer PUBLISH_NUM_ADMIN_FRONT = 3;//show it in the admin page and front page
	
	public static final Integer STATUS_CCWEB = 1; //just show the course in ccweb
	public static final Integer STATUS_CCWEB_COURSES = 2;//both show
	
	public static final Integer STATUS_COURSE_RESOURCE = 1;// show the resource in course
	public static final Integer STATUS_RESOURCE = 2; //show the resource in teacher resource
	public static final Integer STATUS_RESOURCE_DESTORY = 3; //the resource has been deleted;
	
	public static final String STATUS_RESOURCETYPE = "rt";// just show the type in resource page
	public static final String STATUS_COURSE_RESOURCETYPE ="crt"; // just show the type in course resource page
	
	
	public static final int COURSE_COVER_WIDTH = 260;// course cover width
	public static final int COURSE_COVER_HEIGHT = 190;
	
	public static final int TEACHER_PHOTO_WIDTH = 100;//enterprises' teacher photo width;
	public static final int TEACHER_PHOTO_HEIGHT = 100;
	
	// announcement photo's width and height
	public static final int ANNOUNCEMENT_PHOTO_WIDTH = 440;
	public static final int ANNOUNCEMENT_PHOTO_HEIGHT = 220;
	
	// receive msg types
	public static final String MSG_TYPES_COMMENT = "comment";
	public static final String MSG_TYPES_MESSAGE = "message";
	public static final String MSG_TYPES_FOCUS = "focus";
	
	//super admin's pwd
	public static final String KEFU_ADMIN_PWD = "caicai6688";
	
	// patent status
	public static final Integer PATENT_WAITE = 0;
	public static final Integer PATENT_PASS = 1;
	
	// the patent show status in home page
	public static final Integer PATENT_HOME_FOCUS = 1;
	public static final Integer PATENT_HOME_FOCUS_NOT = 0;
	
	// the patent belong to china or foreign
	public static final Integer PATENT_CHINA = 0;
	public static final Integer PATENT_FOREIGN = 1;
	
	// activity photo's width and height
	public static final int ACTIVITY_PHOTO_WIDTH = 440;
	public static final int ACTIVITY_PHOTO_HEIGHT = 220;
	
	// requirement status
	public static final Integer REQUIREMENT_WAITE = 0;
	public static final Integer REQUIREMENT_PASS = 1;
	
	
	public static Map<String,String> getUserEduExpMap(){
		Map<String,String> map = new HashMap<String, String>();
		map.put("1", "幼儿园");
		map.put("2", "小学");
		map.put("3", "初中");
		map.put("4", "高中");
		map.put("5", "本科");
		map.put("6", "研究生");
		return map;
	}
}
