package com.knet51.ccweb.jpa.entities.teacher;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class TeacherHonor extends AbstractEntity {
	@Expose
	private String name;
	@Expose
	private String reason;
	@Expose
	@Lob
	private String detailDesc;
	@ManyToOne
	private User user;
	
	private String forbidden;
	
	
	
	public String getDetailDesc() {
		return detailDesc;
	}
	public void setDetailDesc(String detailDesc) {
		this.detailDesc = detailDesc;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
