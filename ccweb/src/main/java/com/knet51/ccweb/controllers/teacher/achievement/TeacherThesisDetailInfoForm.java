package com.knet51.ccweb.controllers.teacher.achievement;
import javax.validation.constraints.Max;

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
