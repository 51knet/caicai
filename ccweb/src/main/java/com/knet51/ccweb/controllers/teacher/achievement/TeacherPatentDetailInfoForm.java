package com.knet51.ccweb.controllers.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherPatentDetailInfoForm {
	@NotEmpty
	private String inventer;
	@NotEmpty
	private String name;
	@NotEmpty
	private String type;
	@NotEmpty
	private String number;
	public String getInventer() {
		return inventer;
	}
	public void setInventer(String inventer) {
		this.inventer = inventer;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	
}
