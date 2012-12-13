package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherProject extends AbstractEntity {
	
	private String title;
	private String source;
	private String startTime;
	private String endTime;
	
	@ManyToOne
	private Teacher teacher;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public TeacherProject() {
		super();
	}
	
}
