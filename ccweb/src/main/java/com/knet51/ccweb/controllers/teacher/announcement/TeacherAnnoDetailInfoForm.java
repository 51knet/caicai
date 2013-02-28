package com.knet51.ccweb.controllers.teacher.announcement;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherAnnoDetailInfoForm {
	@NotEmpty
	private String title;
	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
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
