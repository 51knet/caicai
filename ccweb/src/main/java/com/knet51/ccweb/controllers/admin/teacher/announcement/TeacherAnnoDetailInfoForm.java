package com.knet51.ccweb.controllers.admin.teacher.announcement;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherAnnoDetailInfoForm {
	@NotEmpty
	@Size(min=1,max=50)
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
}