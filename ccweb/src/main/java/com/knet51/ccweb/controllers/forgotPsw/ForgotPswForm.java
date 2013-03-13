package com.knet51.ccweb.controllers.forgotPsw;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ForgotPswForm {

	
	@NotEmpty @Email
	private String forgotemail;

	public String getForgotemail() {
		return forgotemail;
	}

	public void setForgotemail(String forgotemail) {
		this.forgotemail = forgotemail;
	}
	
	
	
}
