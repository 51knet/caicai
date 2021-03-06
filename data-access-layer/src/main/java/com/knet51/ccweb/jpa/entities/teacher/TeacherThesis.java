package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherThesis extends AbstractEntity {
	@Expose
	@Lob
	private String content;
	private String date;
	private String forbidden;
	@ManyToOne
	private Teacher teacher;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public TeacherThesis() {
		super();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	
	
}
