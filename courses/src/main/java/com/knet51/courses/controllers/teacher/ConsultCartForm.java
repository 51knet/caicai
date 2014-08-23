package com.knet51.courses.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;

public class ConsultCartForm {
	@NotEmpty
	private String phone;
	@NotEmpty
	private String email;
	@NotEmpty
	private String username;
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ConsultCartForm() {
		super();
	}
	
	
}
