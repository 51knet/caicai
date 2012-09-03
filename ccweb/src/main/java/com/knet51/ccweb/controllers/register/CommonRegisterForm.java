package com.knet51.ccweb.controllers.register;

public class CommonRegisterForm {

	private String email;
	private String psw;
	private String confirmpsw;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String password) {
		this.psw = password;
	}

	public String getConfirmpsw() {
		return confirmpsw;
	}

	public void setConfirmpsw(String confirmpsw) {
		this.confirmpsw = confirmpsw;
	}
}
