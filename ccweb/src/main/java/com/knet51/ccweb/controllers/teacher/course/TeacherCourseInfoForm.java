package com.knet51.ccweb.controllers.teacher.course;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherCourseInfoForm {
	@NotEmpty
	@Size(max=50)
	private String courseName;
	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
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
