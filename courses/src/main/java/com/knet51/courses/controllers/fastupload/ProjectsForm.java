package com.knet51.courses.controllers.fastupload;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class ProjectsForm {
	@NotEmpty
	@Size(min=1, max=200)
	private String projectsName;
	@NotEmpty
	@Size(min=1, max=200)
	private String projectsProgress;
	@NotEmpty
	@Size(min=1, max=200)
	private String totalMoney;
	@NotEmpty
	private String projectsContent;
	@NotEmpty
	@Size(min=1, max=200)
	private String projectsCompany;
	@NotEmpty
	@Size(min=1, max=200)
	private String empNumber;
	@NotEmpty
	@Size(min=1, max=200)
	private String location;
	@NotEmpty
	@Size(min=1, max=200)
	private String projectsBoss;
	@NotEmpty
	@Size(min=7, max=11)
	private String projectsPhone;
	@NotEmpty
	@Size(min=1, max=2)
	private String maxInvestNum;
	
	@NotEmpty
	@Size(min=1)
	private String minMoney;
	
	@NotEmpty
	@Email
	private String projectsEmail;

	

	public String getProjectsEmail() {
		return projectsEmail;
	}

	public void setProjectsEmail(String projectsEmail) {
		this.projectsEmail = projectsEmail;
	}

	public String getMaxInvestNum() {
		return maxInvestNum;
	}

	public void setMaxInvestNum(String maxInvestNum) {
		this.maxInvestNum = maxInvestNum;
	}

	public String getMinMoney() {
		return minMoney;
	}

	public void setMinMoney(String minMoney) {
		this.minMoney = minMoney;
	}

	public String getProjectsName() {
		return projectsName;
	}

	public void setProjectsName(String projectsName) {
		this.projectsName = projectsName;
	}

	public String getProjectsProgress() {
		return projectsProgress;
	}

	public void setProjectsProgress(String projectsProgress) {
		this.projectsProgress = projectsProgress;
	}

	public String getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}

	public String getProjectsContent() {
		return projectsContent;
	}

	public void setProjectsContent(String projectsContent) {
		this.projectsContent = projectsContent;
	}

	public String getProjectsCompany() {
		return projectsCompany;
	}

	public void setProjectsCompany(String projectsCompany) {
		this.projectsCompany = projectsCompany;
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

	public String getProjectsBoss() {
		return projectsBoss;
	}




	public void setProjectsBoss(String projectsBoss) {
		this.projectsBoss = projectsBoss;
	}




	public String getProjectsPhone() {
		return projectsPhone;
	}




	public void setProjectsPhone(String projectsPhone) {
		this.projectsPhone = projectsPhone;
	}




	public ProjectsForm() {
		super();
	}
	
}
