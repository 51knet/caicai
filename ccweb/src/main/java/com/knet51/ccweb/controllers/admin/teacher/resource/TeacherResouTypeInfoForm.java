package com.knet51.ccweb.controllers.admin.teacher.resource;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherResouTypeInfoForm {
	
	@NotEmpty
	@Size(min=1,max=50)
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
