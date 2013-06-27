package com.knet51.ccweb.controllers.admin.teacher.resume;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

public class SelfUrlForm {

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
