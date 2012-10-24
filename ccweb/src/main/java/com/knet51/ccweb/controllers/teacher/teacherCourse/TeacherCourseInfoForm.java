package com.knet51.ccweb.controllers.teacher.teacherCourse;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherCourseInfoForm {
	@NotEmpty
	private String courseName;
	@NotEmpty
	private String courseDesc;
	
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
}
