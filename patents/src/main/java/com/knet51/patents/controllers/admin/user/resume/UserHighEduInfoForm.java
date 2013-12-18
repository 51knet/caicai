package com.knet51.patents.controllers.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserHighEduInfoForm {
	@NotEmpty
	@Size(max=25)
	private String highSchoolName;
	
	@NotEmpty
	@Size(max=25)
	private String highCollegeName;
	
	@NotEmpty
	@Size(max=25)
	private String highMajor;

	@NotEmpty
	@Size(max=25)
	private String highStartTime;
	
	@NotEmpty
	@Size(max=25)
	private String highTeacherName;
		
	@NotEmpty
	@Size(max=25)
	private String highClassNum;

	public String getHighSchoolName() {
		return highSchoolName;
	}



	public void setHighSchoolName(String highSchoolName) {
		this.highSchoolName = highSchoolName;
	}



	public String getHighCollegeName() {
		return highCollegeName;
	}



	public void setHighCollegeName(String highCollegeName) {
		this.highCollegeName = highCollegeName;
	}



	public String getHighMajor() {
		return highMajor;
	}



	public void setHighMajor(String highMajor) {
		this.highMajor = highMajor;
	}



	public String getHighStartTime() {
		return highStartTime;
	}



	public void setHighStartTime(String highStartTime) {
		this.highStartTime = highStartTime;
	}



	public String getHighTeacherName() {
		return highTeacherName;
	}



	public void setHighTeacherName(String highTeacherName) {
		this.highTeacherName = highTeacherName;
	}

	public String getHighClassNum() {
		return highClassNum;
	}



	public void setHighClassNum(String highClassNum) {
		this.highClassNum = highClassNum;
	}



	public UserHighEduInfoForm() {
		super();
	}
	
	
	
	
}
