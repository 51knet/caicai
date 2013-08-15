package com.knet51.ccweb.controllers.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserEduInfoForm {
	@NotEmpty
	@Size(max=25)
	private String schoolName;
	
	@Size(max=25)
	private String collegeName;
	
	@Size(max=25)
	private String major;

	@NotEmpty
	@Size(max=25)
	private String startTime;
	
	@NotEmpty
	@Size(max=25)
	private String teacherName;
	
	@NotEmpty
	private String level;
	
	@NotEmpty
	@Size(max=25)
	private String classNum;

	public String getSchoolName() {
		return schoolName;
	}

	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getClassNum() {
		return classNum;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public UserEduInfoForm() {
		super();
	}
	
	
	
	
}
