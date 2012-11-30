package com.knet51.ccweb.controllers.student;

import org.hibernate.validator.constraints.NotEmpty;

public class StudentPersonalInfoForm {
	@NotEmpty
	private String name;
	@NotEmpty
	private String gender;
	private String role;
	@NotEmpty
	private String college;
	@NotEmpty
	private String junior_high_school;
	@NotEmpty
	private String senior_high_school;
	@NotEmpty
	private String primary_school;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
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
	public String getJunior_high_school() {
		return junior_high_school;
	}
	public void setJunior_high_school(String junior_high_school) {
		this.junior_high_school = junior_high_school;
	}
	public String getSenior_high_school() {
		return senior_high_school;
	}
	public void setSenior_high_school(String senior_high_school) {
		this.senior_high_school = senior_high_school;
	}
	public String getPrimary_school() {
		return primary_school;
	}
	public void setPrimary_school(String primary_school) {
		this.primary_school = primary_school;
	}
	
}
