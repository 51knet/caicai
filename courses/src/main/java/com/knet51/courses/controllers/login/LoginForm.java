package com.knet51.courses.controllers.login;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {

	
	@NotEmpty @Email
	private String email;
	@NotEmpty 
	private String password;
	private int remeberMe = 0;
	
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
	
	@Override
	public String toString() {
		return "LoginForm [email=" + email + ", password=" + password
				+ ", remeberMe=" + remeberMe + "]";
	}
	public int getRemeberMe() {
		return remeberMe;
	}
	public void setRemeberMe(int remeberMe) {
		this.remeberMe = remeberMe;
	}
	
}
