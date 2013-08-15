package com.knet51.ccweb.controllers.admin.user.resume;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserWorkExpInfoForm {
	@NotEmpty
	@Size(max=25)
	private String company;
	@NotEmpty
	@Size(max=25)
	private String position;
	@NotEmpty
	@Size(max=25)
	private String startTime;
	@NotEmpty
	@Size(max=25)
	private String endTime;
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public UserWorkExpInfoForm() {
		super();
	}
	
	
}
