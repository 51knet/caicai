package com.graphene.web.controller.admin.kefu.announcement;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class AnnouncementForm {
	@NotEmpty
	@Size(min=1,max=50)
	private String title;
	@NotEmpty
	private String content;
	private String type;
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
