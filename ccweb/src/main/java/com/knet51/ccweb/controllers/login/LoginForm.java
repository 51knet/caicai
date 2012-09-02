package com.knet51.ccweb.controllers.login;

public class LoginForm {

	private String email;
	private String password;
	private Boolean remeberMe = Boolean.FALSE;
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Boolean getRemeberMe() {
		return remeberMe;
	}
	public void setRemeberMe(Boolean remeberMe) {
		this.remeberMe = remeberMe;
	}
}
