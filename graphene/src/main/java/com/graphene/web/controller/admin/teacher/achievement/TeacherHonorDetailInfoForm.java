package com.graphene.web.controller.admin.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherHonorDetailInfoForm {
	//@NotEmpty
	//@Size(max=25)
	private String honorName;
	//@NotEmpty
	//@Size(max=25)
	private String reason;
	@NotEmpty
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
