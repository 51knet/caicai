package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherCourse extends AbstractEntity {
	
	private String courseName;
	private String courseDesc;
	private String courseDate;
	private String courseCover;
	private String courseType; 
	@ManyToOne
	private Teacher teacher;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	
	public String getCourseDate() {
		return courseDate;
	}
	public void setCourseDate(String courseDate) {
		this.courseDate = courseDate;
	}
	
	public String getCourseCover() {
		return courseCover;
	}
	public void setCourseCover(String courseCover) {
		this.courseCover = courseCover;
	}
	
	
	public String getCourseType() {
		return courseType;
	}
	public void setCourseType(String courseType) {
		this.courseType = courseType;
	}
	public TeacherCourse() {
		super();
	}
	
	
}
