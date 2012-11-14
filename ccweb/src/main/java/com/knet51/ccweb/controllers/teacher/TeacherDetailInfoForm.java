package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherDetailInfoForm {

	@NotEmpty
	private String name;
	@NotEmpty
	private String gender;
	@NotEmpty
	private String course;

	private String university_province;
	private String university_city;
	private String university;
	private String college;
	private String title;
	private String coachType;

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

	public String getCourse() {
		return course;
	}

	public void setCourse(String course) {
		this.course = course;
	}

	public String getUniversity_province() {
		return university_province;
	}

	public void setUniversity_province(String university_province) {
		this.university_province = university_province;
	}

	public String getUniversity_city() {
		return university_city;
	}

	public void setUniversity_city(String university_city) {
		this.university_city = university_city;
	}

	public String getUniversity() {
		return university;
	}

	public void setUniversity(String university) {
		this.university = university;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCoachType() {
		return coachType;
	}

	public void setCoachType(String coachType) {
		this.coachType = coachType;
	}
}
