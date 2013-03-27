package com.knet51.ccweb.controllers.enterprise;

import org.hibernate.validator.constraints.NotEmpty;

public class EnterpriseTeacherInfoForm {
	@NotEmpty
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
