package com.knet51.ccweb.controllers.enterprise;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class EnterprisePersonalInfoForm {

	@NotEmpty
	@Size(max=25)
	private String name;
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	

}
