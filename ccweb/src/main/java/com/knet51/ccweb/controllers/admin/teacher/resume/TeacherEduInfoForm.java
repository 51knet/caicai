package com.knet51.ccweb.controllers.admin.teacher.resume;

import org.hibernate.validator.constraints.NotEmpty;



public class TeacherEduInfoForm {
	//@NotEmpty
	//@Size(max=25)
	private String schoolName;
	//@NotEmpty
	//@Size(max=25)
	private String collegeName;
	//@NotEmpty
	//@Size(max=25)
	private String degree;

	//@NotEmpty
	//@Size(max=25)
	private String startTime;
	//@NotEmpty
	//@Size(max=25)
	private String endTime;
	@NotEmpty
	private String educationDesc;

	
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
	public String getEducationDesc() {
		return educationDesc;
	}
	public void setEducationDesc(String educationDesc) {
		this.educationDesc = educationDesc;
	}
	public TeacherEduInfoForm() {
		super();
	}

}
