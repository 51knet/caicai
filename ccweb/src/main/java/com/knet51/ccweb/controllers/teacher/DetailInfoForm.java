package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;

public class DetailInfoForm {

	@NotEmpty
	private String role;
	@NotEmpty
	private String college;
	@NotEmpty
	private String major;

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

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

}
