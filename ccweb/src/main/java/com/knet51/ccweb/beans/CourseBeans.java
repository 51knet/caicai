package com.knet51.ccweb.beans;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class CourseBeans {
	private Teacher teacher;
	//private String teacherName;
	private TeacherCourse course;

//	public String getTeacherName() {
//		return teacherName;
//	}
//	public void setTeacherName(String teacherName) {
//		this.teacherName = teacherName;
//	}
	public TeacherCourse getCourse() {
		return course;
	}
	public void setCourse(TeacherCourse course) {
		this.course = course;
	}
	
//	public String getSchoolName() {
//		return schoolName;
//	}
//	public void setSchoolName(String schoolName) {
//		this.schoolName = schoolName;
//	}
	
	
	public CourseBeans(Teacher teacher, /*String schoolName,  String teacherName,*/
			TeacherCourse course) {
		super();
		//this.schoolName = schoolName;
		//this.teacherName = teacherName;
		this.course = course;
		this.teacher = teacher;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public CourseBeans() {
		super();
	}
	
}
