package com.knet51.ccweb.controllers.teacher;

import javax.persistence.Column;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherPersonalInfoForm {

	@NotEmpty
	@Size(min=3,max=25)
	private String name;
	@NotEmpty
	private String gender;
	@NotEmpty
	@Size(min=3,max=25)
	private String college;
	@NotEmpty
	@Size(min=3,max=25)
	private String school;
	// private String university_province;
	// private String university_city;
	@Size(min=3,max=25)
	private String title;
	@Size(min=3,max=25)
	private String major;
	@Size(min=3,max=25)
	private String role;

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

	// public String getUniversity_province() {
	// return university_province;
	// }
	//
	// public void setUniversity_province(String university_province) {
	// this.university_province = university_province;
	// }
	//
	// public String getUniversity_city() {
	// return university_city;
	// }
	//
	// public void setUniversity_city(String university_city) {
	// this.university_city = university_city;
	// }

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
