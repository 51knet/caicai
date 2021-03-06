package com.knet51.courses.beans;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Enterprise;
import com.knet51.ccweb.jpa.entities.Student;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.UserRight;

public class UserInfo {

	private User user;
	private Teacher teacher;
	private Student student;
	//private Announcement announcement;
	private List<UserRight> userRight;

	public UserInfo(User user) {
		this.user = user;
		this.teacher = null;
		this.student = null;
	}

	
	public List<UserRight> getUserRight() {
		return userRight;
	}


	public void setUserRight(List<UserRight> userRight) {
		this.userRight = userRight;
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

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

//	public Announcement getAnnouncement() {
//		return announcement;
//	}
//
//	public void setAnnouncement(Announcement announcement) {
//		this.announcement = announcement;
//	}
	

	public String getName(){
		return this.user.getName();
	}
	
	public String getGender(){
		return this.user.getGender();
	}
	public String getTeacherRole(){
		return this.teacher.getRole();
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
	
	public String getCollege(){
		return this.teacher.getCollege();
	}
	
	public String getSchool(){
		return this.teacher.getSchool();
	}
	 
	public String getIsEnterprise(){
		return this.teacher.getIsEnterprise();
	}
}
