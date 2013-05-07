package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
@Entity
public class Authentication extends AbstractEntity {
	
	private String title;
	private String content;
	private Date date;
	private String resourceurl;
	private String fileName;
	private String status;
	private String reason;
	private String email;
	@ManyToOne
	private User user;
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getResourceurl() {
		return resourceurl;
	}
	public void setResourceurl(String resourceurl) {
		this.resourceurl = resourceurl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
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
	public Authentication() {
		super();
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
