package com.knet51.ccweb.controllers.admin.caicai;

import org.hibernate.validator.constraints.NotEmpty;

public class AuthenticationRefuseForm {
	@NotEmpty
	private String reason;

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
	
	
}
