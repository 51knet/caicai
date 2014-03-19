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
	
	@NotEmpty
	@Size(min=1, max=2)
	private String maxInvestNum;
	
	@NotEmpty
	@Size(min=1)
	private String minMoney;
	
	private String targetUser;
	private String targetReq;
	private String modulIntro;
	private String profitModul;
	private String competitorIntro;
	private String coreValueIntro;
	private String shareholderIntro;
	private String unShareholderIntro;
	private String planContext;
	
	public String getProjectName() {
		return projectName;
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

	public String getTargetUser() {
		return targetUser;
	}


	public void setTargetUser(String targetUser) {
		this.targetUser = targetUser;
	}

	public String getTargetReq() {
		return targetReq;
	}


	public void setTargetReq(String targetReq) {
		this.targetReq = targetReq;
	}


	public String getModulIntro() {
		return modulIntro;
	}


	public void setModulIntro(String modulIntro) {
		this.modulIntro = modulIntro;
	}


	public String getProfitModul() {
		return profitModul;
	}


	public void setProfitModul(String profitModul) {
		this.profitModul = profitModul;
	}


	public String getCompetitorIntro() {
		return competitorIntro;
	}


	public void setCompetitorIntro(String competitorIntro) {
		this.competitorIntro = competitorIntro;
	}


	public String getCoreValueIntro() {
		return coreValueIntro;
	}


	public void setCoreValueIntro(String coreValueIntro) {
		this.coreValueIntro = coreValueIntro;
	}


	public String getShareholderIntro() {
		return shareholderIntro;
	}


	public void setShareholderIntro(String shareholderIntro) {
		this.shareholderIntro = shareholderIntro;
	}


	public String getUnShareholderIntro() {
		return unShareholderIntro;
	}


	public void setUnShareholderIntro(String unShareholderIntro) {
		this.unShareholderIntro = unShareholderIntro;
	}


	public String getPlanContext() {
		return planContext;
	}


	public void setPlanContext(String planContext) {
		this.planContext = planContext;
	}


	public ProjectsForm() {
		super();
	}
	
}
