package com.knet51.ccweb.controllers.teacher.achievement;

import javax.validation.constraints.Max;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherHonorDetailInfoForm {
	@NotEmpty
	private String honorName;
	@NotEmpty
	private String reason;
	@NotEmpty
	@Max(value = 10000, message="overflow 10000!!")
	private String honorDesc;
	
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
	public String getHonorDesc() {
		return honorDesc;
	}
	public void setHonorDesc(String honorDesc) {
		this.honorDesc = honorDesc;
	}
	
}
