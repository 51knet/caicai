package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class TeacherHonor extends AbstractEntity {
	@Expose
	private String name;
	@Expose
	private String reason;
	@Expose
	@Lob
	@Column(length=10000)
	private String detailDesc;
	@ManyToOne
	private Teacher teacher;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
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
	public TeacherHonor() {
		super();
	}
	
	
}
