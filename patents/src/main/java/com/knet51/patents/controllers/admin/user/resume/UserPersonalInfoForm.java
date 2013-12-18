package com.knet51.patents.controllers.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserPersonalInfoForm {

	@NotEmpty
	@Size(max=25)
	private String name;
	@NotEmpty
	private String gender;
	private String college;
	// private String university_province;
	// private String university_city;
	private String classNum;
	private String graduateTime;
	private String teacher;
	private String major;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public String getGraduateTime() {
		return graduateTime;
	}

	public void setGraduateTime(String graduateTime) {
		this.graduateTime = graduateTime;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}



}
