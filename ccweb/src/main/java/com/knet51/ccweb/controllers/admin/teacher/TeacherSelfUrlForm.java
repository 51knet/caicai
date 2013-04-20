package com.knet51.ccweb.controllers.admin.teacher;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherSelfUrlForm {

	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
