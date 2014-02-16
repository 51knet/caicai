package com.knet51.patents.controllers.admin.projects;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class ProjectsForm {
	@NotEmpty
	@Size(min=1, max=200)
	private String projectName;
	@NotEmpty
	private String industry;//hang ye
	@NotEmpty
	@Size(min=1, max=200)
	private String progess;//jin du
	@NotEmpty
	@Size(min=1, max=200)
	private String companyName; 
	private String empNumber; // yuan gong ren shu
	@NotEmpty
	@Size(min=1, max=200)
	private String location;
	
	@NotEmpty
	private String totalMoney;// zong jin e
	@NotEmpty
	private String content; // qi ye jian jie
	
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getIndustry() {
		return industry;
	}
	public void setIndustry(String industry) {
		this.industry = industry;
	}
	public String getProgess() {
		return progess;
	}
	public void setProgess(String progess) {
		this.progess = progess;
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
	
	protected ProjectsForm() {
		super();
	}
	
}
