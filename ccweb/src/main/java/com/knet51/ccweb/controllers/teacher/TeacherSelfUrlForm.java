package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherSelfUrlForm {

	@NotEmpty
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

}
