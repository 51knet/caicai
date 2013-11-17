package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Requirement extends AbstractEntity {
	private String title;
	@Lob
	private String content;
	private Date date; 
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private RequirType requirType;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public Requirement() {
		super();
	}

	public RequirType getRequirType() {
		return requirType;
	}

	public void setRequirType(RequirType requirType) {
		this.requirType = requirType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
