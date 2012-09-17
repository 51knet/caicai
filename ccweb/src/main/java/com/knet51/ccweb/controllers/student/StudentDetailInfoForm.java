package com.knet51.ccweb.controllers.student;

import org.hibernate.validator.constraints.NotEmpty;

public class StudentDetailInfoForm {

	@NotEmpty
	private String role;
	@NotEmpty
	private String college;

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

}
