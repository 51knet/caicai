package com.knet51.ccweb.controllers.teacher.resource;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherResouTypeInfoForm {
	
	@NotEmpty
	private String typeName;

	public TeacherResouTypeInfoForm(String typeName) {
		super();
		this.typeName = typeName;
	}

	public TeacherResouTypeInfoForm() {
		super();
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	
	
}
