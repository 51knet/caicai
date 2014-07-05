package com.graphene.web.controller.admin.teacher.achievement;

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
