package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;


public class TeacherEduInfoForm {
	@NotEmpty
	private String school;
	@NotEmpty
	private String college;
	@NotEmpty
	private String degree;
	@NotEmpty
	private String startTime;
	@NotEmpty
	private String endTime;
	public String getSchool() {
		return school;
	}
	public void setSchool(String school) {
		this.school = school;
	}
	public String getCollege() {
		return college;
	}
	public void setCollege(String college) {
		this.college = college;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public TeacherEduInfoForm() {
		super();
	}
	
	

}
