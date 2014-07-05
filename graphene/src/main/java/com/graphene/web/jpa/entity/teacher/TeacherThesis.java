package com.graphene.web.jpa.entity.teacher;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.google.gson.annotations.Expose;
import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;


@Entity
public class TeacherThesis extends AbstractEntity {
	@Expose
	@Lob
	private String content;
	private String date;
	private String forbidden;
	@ManyToOne
	private User user;

	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}


	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
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
