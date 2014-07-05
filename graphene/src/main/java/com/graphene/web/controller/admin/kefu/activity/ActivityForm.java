package com.graphene.web.controller.admin.kefu.activity;

import org.hibernate.validator.constraints.NotEmpty;

public class ActivityForm {
	@NotEmpty
	private String title;
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
	public ActivityForm() {
		super();
	}
	
	
}
