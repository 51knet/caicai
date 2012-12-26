package com.knet51.courses.beans;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class CourseBeans {
	private Integer userCount;
	private Double courseMark;
	
	private TeacherCourse teacherCourse;
	
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
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}
	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}
	public CourseBeans(Integer userCount, Double courseMark,
			TeacherCourse teacherCourse) {
		super();
		this.userCount = userCount;
		this.courseMark = courseMark;
		this.teacherCourse = teacherCourse;
	}
	public CourseBeans() {
		super();
	}
	
	
}
