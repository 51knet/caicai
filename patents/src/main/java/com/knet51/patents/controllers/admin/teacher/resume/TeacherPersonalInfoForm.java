package com.knet51.patents.controllers.admin.teacher.resume;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherPersonalInfoForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String college;
	@NotEmpty
	private String school;
	private String title;
	private String major;
	private String role;
	@NotEmpty
	private String infor;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfor() {
		return infor;
	}

	public void setInfor(String infor) {
		this.infor = infor;
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
