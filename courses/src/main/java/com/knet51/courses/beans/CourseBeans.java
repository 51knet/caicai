package com.knet51.courses.beans;

import com.knet51.ccweb.jpa.entities.courses.Course;

public class CourseBeans {
	private Integer userCount;
	private Double courseMark;
	
	private Course course;
	
	public Integer getUserCount() {
		return userCount;
	}
	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}
	public Double getCourseMark() {
		return courseMark;
	}
	public void setCourseMark(Double courseMark) {
		this.courseMark = courseMark;
	}
	public Course getTeacherCourse() {
		return course;
	}
	public void setTeacherCourse(Course course) {
		this.course = course;
	}
	public CourseBeans(Integer userCount, Double courseMark,
			Course course) {
		super();
		this.userCount = userCount;
		this.courseMark = courseMark;
		this.course = course;
	}
	public CourseBeans() {
		super();
	}
	
	
}
