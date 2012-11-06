package com.knet51.ccweb.controllers.teacher.resource;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherResouInfoForm {
	
	@NotEmpty
	private String typeName;

	public TeacherResouInfoForm(String typeName) {
		super();
		this.typeName = typeName;
	}

	public TeacherResouInfoForm() {
		super();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
