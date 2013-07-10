package com.knet51.ccweb.controllers.admin.user;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class MyTrendsForm {
	@NotEmpty 
	@Size(min=1, max=400)
	private String contents;

	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}
	
}
