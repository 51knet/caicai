package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherPatent  extends AbstractEntity {
	private String inventer;
	private String name;
	private String type;
	private String number;
	private String detailDesc;
	@ManyToOne
	private Teacher teacher;
	public String getInventer() {
		return inventer;
	}
	public void setInventer(String inventer) {
		this.inventer = inventer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public Teacher getTeacher() {
		return teacher;
	}
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}
	public String getDesc() {
		return detailDesc;
	}
	public void setDesc(String desc) {
		this.detailDesc = desc;
	}
	public TeacherPatent() {
		super();
	}
	
	
}
