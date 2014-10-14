package com.knet51.diplomat.controllers.admin.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherThesisDetailInfoForm {
	@NotEmpty
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
}
