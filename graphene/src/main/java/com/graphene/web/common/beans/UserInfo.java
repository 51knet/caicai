package com.graphene.web.common.beans;

import com.graphene.web.jpa.entity.teacher.Teacher;
import com.graphene.web.jpa.entity.user.User;



public class UserInfo {

	private User user;
	private Teacher teacher;
	public UserInfo(User user) {
		this.user = user;
	}

	public Long getId() {
		return this.user.getId();
	}

	public String getEmail() {
		return this.user.getEmail();
	}

	public String getRole() {
		return this.user.getRole();
	}

	public String getPhotoUrl() {
		return this.user.getPhoto_url();
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getName(){
		return this.user.getName();
	}
	
	public String getGender(){
		return this.user.getGender();
	}

	public UserInfo() {
		super();
	}
	public String getAvatar() {
		return this.user.getPhoto_url();
	}
	public String getFax(){
		return this.user.getFax();
	}
	
	public String getAddress(){
		return this.user.getAddress();
	}
	
	public String getPhone(){
		return this.user.getFix_phone();
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	protected UserInfo(User user, Teacher teacher) {
		super();
		this.user = user;
		this.teacher = teacher;
	}

}
