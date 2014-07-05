package com.graphene.web.controller.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLowEduInfoForm {
	@NotEmpty
	@Size(max=25)
	private String lowSchoolName;

	@NotEmpty
	@Size(max=25)
	private String lowStartTime;
	
	@NotEmpty
	@Size(max=25)
	private String lowTeacherName;
	
	@NotEmpty
	@Size(max=25)
	private String lowClassNum;

	public String getLowSchoolName() {
		return lowSchoolName;
	}



	public void setLowSchoolName(String lowSchoolName) {
		this.lowSchoolName = lowSchoolName;
	}



	public String getLowStartTime() {
		return lowStartTime;
	}



	public void setLowStartTime(String lowStartTime) {
		this.lowStartTime = lowStartTime;
	}



	public String getLowTeacherName() {
		return lowTeacherName;
	}



	public void setLowTeacherName(String lowTeacherName) {
		this.lowTeacherName = lowTeacherName;
	}



	public String getLowClassNum() {
		return lowClassNum;
	}



	public void setLowClassNum(String lowClassNum) {
		this.lowClassNum = lowClassNum;
	}



	public UserLowEduInfoForm() {
		super();
	}
	
	
	
	
}
