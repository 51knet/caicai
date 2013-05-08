package com.knet51.ccweb.controllers.common.defs;

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
}
