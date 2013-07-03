package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Student {
	@Id
	private Long id; /* manually set id to user's id */
	//@OneToOne(cascade = CascadeType.ALL)
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
	private User user;
	private String role;
	private String teacher;
	private String college;
	private String graduateTime;
	private String classNum;
	private String major;
	protected Student() {

	}
	public Student(User user) {
		this.user = user;
		setId(user.getId());
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getGraduateTime() {
		return graduateTime;
	}
	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	
	
}