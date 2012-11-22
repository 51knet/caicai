package com.knet51.ccweb.controllers.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherHonorDetailInfoForm {
	@NotEmpty
	private String honorName;
	@NotEmpty
	private String reason;
	
	public String getHonorName() {
		return honorName;
	}
	public void setHonorName(String honorName) {
		this.honorName = honorName;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	
}
