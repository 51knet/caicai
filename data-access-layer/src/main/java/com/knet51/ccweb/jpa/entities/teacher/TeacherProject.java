package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherProject extends AbstractEntity {
	@Expose
	private String title;
	@Expose
	private String source;
	@Expose
	private String startTime;
	@Expose
	private String endTime;
	@Expose
	private String detailDesc;
	
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
	public String getDesc() {
		return detailDesc;
	}
	public void setDesc(String projectDesc) {
		this.detailDesc = projectDesc;
	}
	public TeacherProject() {
		super();
	}
	
}
