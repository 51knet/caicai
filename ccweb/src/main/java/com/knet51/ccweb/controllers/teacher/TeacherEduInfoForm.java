package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;


public class TeacherEduInfoForm {
	@NotEmpty
	private String schoolName;
	@NotEmpty
	private String collegeName;
	@NotEmpty
	private String degree;
	@NotEmpty
	private String startTime;
	@NotEmpty
	private String endTime;

	
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
