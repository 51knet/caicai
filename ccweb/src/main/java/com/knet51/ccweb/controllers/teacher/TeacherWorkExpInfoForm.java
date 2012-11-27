package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;


public class TeacherWorkExpInfoForm {
	@NotEmpty
	private String company;
	@NotEmpty
	private String department;
	@NotEmpty
	private String position;
	@NotEmpty
	private String startTimeName;
	@NotEmpty
	private String endTimeName;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public TeacherWorkExpInfoForm() {
		super();
	}
	public String getStartTimeName() {
		return startTimeName;
	}
	public void setStartTimeName(String startTimeName) {
		this.startTimeName = startTimeName;
	}
	public String getEndTimeName() {
		return endTimeName;
	}
	public void setEndTimeName(String endTimeName) {
		this.endTimeName = endTimeName;
	}
	
	

}
