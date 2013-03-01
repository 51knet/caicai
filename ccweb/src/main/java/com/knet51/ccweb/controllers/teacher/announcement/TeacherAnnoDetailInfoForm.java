package com.knet51.ccweb.controllers.teacher.announcement;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherAnnoDetailInfoForm {
	@NotEmpty
	@Size(min=3,max=50)
	private String title;
	@NotEmpty
	@Size(min=10,max=10000)
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
}
