package com.knet51.courses.beans;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class TeacherCourseBeans {
	
	private Teacher teacher;
	private TeacherCourse teacherCourse;
	
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public TeacherCourse getTeacherCourse() {
		return teacherCourse;
	}
	public void setTeacherCourse(TeacherCourse teacherCourse) {
		this.teacherCourse = teacherCourse;
	}
	public TeacherCourseBeans(Teacher teacher, TeacherCourse teacherCourse) {
		super();
		this.teacher = teacher;
		this.teacherCourse = teacherCourse;
	}
	public TeacherCourseBeans() {
		super();
	}
	
	
}
