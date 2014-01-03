package com.knet51.courses.controllers.defs;

public final class GlobalDefs {
	public static final String SESSION_USER_INFO = "sessionUserInfo";
	public static final String COOKIE_IDENTITY = "identity";
	public static final Integer PUBLISH_NUM_DELETE = 0;//course is deleted,but in database, it still exit 
	public static final Integer PUBLISH_NUM_RECYCLE = 1; //just show it in teacher recover page
	public static final Integer PUBLISH_NUM_ADMIN= 2;//just show it in teacher admin page
	public static final Integer PUBLISH_NUM_ADMIN_FRONT = 3;//show it in the admin page and front page
	public static final Integer STATUS_CCWEB = 1; //just show the course in ccweb
	public static final Integer STATUS_CCWEB_COURSES = 2;//both show
	
	
	public static final Integer STATUS_COURSE_RESOURCE = 1;// show the resource in course
	public static final Integer STATUS_RESOURCE = 2; //show the resource in teacher resource
	
	public static final Integer HOME_PATENT_COUNT = 2381427;
	public static final Integer HOME_TEACHER_COUNT = 8230;
	public static final Integer HOME_PATENT_TRADE_COUNT = 15482;
	public static final Integer HOME_PATENT_REQUIRE_COUNT = 23647; 
	public static final Integer HOME_PATENT_CN_COUNT = 2163574; 
	
	// the patent show status in home page
	public static final Integer PATENT_HOME_FOCUS = 1;
	public static final Integer PATENT_HOME_FOCUS_NOT = 0;
		
	// the patent belong to china or foreign
	public static final Integer PATENT_CHINA = 0;
	public static final Integer PATENT_FOREIGN = 1;
	
	// requirement status
	public static final Integer REQUIREMENT_WAITE = 0;
	public static final Integer REQUIREMENT_PASS = 1;
}
