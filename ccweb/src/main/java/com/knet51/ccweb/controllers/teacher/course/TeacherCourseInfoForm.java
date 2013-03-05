package com.knet51.ccweb.controllers.teacher.course;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherCourseInfoForm {
	@NotEmpty
	@Size(min=1,max=50)
	private String courseName;
	@NotEmpty
	private String courseDesc;
	
	private String courseType;
	
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getCourseDesc() {
		return courseDesc;
	}
	public void setCourseDesc(String courseDesc) {
		this.courseDesc = courseDesc;
	}
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	
	
}
