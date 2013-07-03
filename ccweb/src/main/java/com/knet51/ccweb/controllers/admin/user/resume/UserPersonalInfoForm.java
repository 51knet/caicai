package com.knet51.ccweb.controllers.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserPersonalInfoForm {

	@NotEmpty
	@Size(max=25)
	private String name;
	@NotEmpty
	private String gender;
	private String college;
	// private String university_province;
	// private String university_city;
	private String senior_high_school;
	private String junior_high_school;
	private String primary_school;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}


	public String getSenior_high_school() {
		return senior_high_school;
	}

	public void setSenior_high_school(String senior_high_school) {
		this.senior_high_school = senior_high_school;
	}

	public String getJunior_high_school() {
		return junior_high_school;
	}

	public void setJunior_high_school(String junior_high_school) {
		this.junior_high_school = junior_high_school;
	}

	public String getPrimary_school() {
		return primary_school;
	}

	public void setPrimary_school(String primary_school) {
		this.primary_school = primary_school;
	}

	

}
