package com.knet51.ccweb.controllers.admin.user.requirement;

import org.hibernate.validator.constraints.NotEmpty;

public class RequireForm {
	@NotEmpty
	private String title;
	
	private String endTime;
	
	@NotEmpty
	private String content;
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
	public RequireForm() {
		super();
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	
	
}
