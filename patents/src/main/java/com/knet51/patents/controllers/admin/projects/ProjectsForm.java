package com.knet51.patents.controllers.admin.projects;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ProjectsForm {
	@NotEmpty
	@Size(min=1, max=200)
	private String projectName;

	@NotEmpty
	@Size(min=1, max=200)
	private String progress;
	@NotEmpty
	@Size(min=1, max=200)
	private String totalMoney;
	@NotEmpty
	private String content;
	@NotEmpty
	@Size(min=1, max=200)
	private String companyName;
	@NotEmpty
	@Size(min=1, max=200)
	private String empNumber;
	@NotEmpty
	@Size(min=1, max=200)
	private String location;
	@NotEmpty
	@Size(min=1, max=200)
	private String boss;
	@NotEmpty
	@Size(min=7, max=11)
	private String phone;
	
	
	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}





	public String getProgress() {
		return progress;
	}


	public void setProgress(String progress) {
		this.progress = progress;
	}


	public String getTotalMoney() {
		return totalMoney;
	}


	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getCompanyName() {
		return companyName;
	}


	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}


	public String getEmpNumber() {
		return empNumber;
	}


	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getBoss() {
		return boss;
	}


	public void setBoss(String boss) {
		this.boss = boss;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public ProjectsForm() {
		super();
	}
	
}
