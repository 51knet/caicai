package com.knet51.ccweb.controllers.admin.teacher.resume;


import org.hibernate.validator.constraints.NotEmpty;


public class TeacherWorkExpInfoForm {
	//@NotEmpty
	//@Size(max=25)
	private String company;
	//@NotEmpty
	//@Size(max=25)
	private String department;
	//@NotEmpty
	//@Size(max=25)
	private String position;
	//@NotEmpty
	//@Size(max=25)
	private String startTimeName;
	//@NotEmpty
	//@Size(max=25)
	private String endTimeName;
	@NotEmpty

	private String workDesc;
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
	public String getWorkDesc() {
		return workDesc;
	}
	public void setWorkDesc(String workDesc) {
		this.workDesc = workDesc;
	}
}
