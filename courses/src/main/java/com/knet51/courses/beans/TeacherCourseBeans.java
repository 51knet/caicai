package com.knet51.courses.beans;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.courses.Course;

public class TeacherCourseBeans {
	
	private Teacher teacher;
	private Course course;
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public Course getTeacherCourse() {
		return course;
	}
	public void setTeacherCourse(Course course) {
		this.course = course;
	}
	public TeacherCourseBeans(Teacher teacher, Course course) {
		super();
		this.teacher = teacher;
		this.course = course;
	}
	public TeacherCourseBeans() {
		super();
	}
	
	
}
