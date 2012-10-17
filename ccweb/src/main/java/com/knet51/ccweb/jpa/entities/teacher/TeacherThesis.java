package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherThesis extends AbstractEntity {
	
	private String content;
	
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
	
}
