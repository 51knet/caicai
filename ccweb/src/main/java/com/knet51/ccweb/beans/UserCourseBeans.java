package com.knet51.ccweb.beans;

import com.knet51.ccweb.jpa.entities.teacher.UserCourse;


public class UserCourseBeans {
	private String userName;
	private String photoUrl;
	private UserCourse userCourse;
	public String getPhotoUrl() {
		return photoUrl;
	}
	public void setPhotoUrl(String photoUrl) {
		this.photoUrl = photoUrl;
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
	public UserCourse getUserCourse() {
		return userCourse;
	}
	public void setUserCourse(UserCourse userCourse) {
		this.userCourse = userCourse;
	}
	public UserCourseBeans(String userName,String photoUrl,UserCourse userCourse) {
		super();
		this.userName=userName;
		this.photoUrl=photoUrl;
		this.userCourse=userCourse;
	}
	public UserCourseBeans() {
		super();
	}
}
