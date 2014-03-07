package com.knet51.courses.controllers.fastupload;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class TechRequireForm {
	@NotEmpty
	@Size(min=1,max=200)
	private String techReqTitle;
	@NotEmpty
	private String techReqEndTime;
	@NotEmpty
	@Size(min=1,max=200)
	private String techReqName;
	@NotEmpty
	private String techReqMoney;
	@NotEmpty
	@Size(min=1,max=200) 
	private String techReqCompany;
	@NotEmpty
	@Size(min=1,max=200)
	private String techReqAddress;
	@NotEmpty
	@Size(min=7,max=11)
	private String techReqPhone;
	@NotEmpty
	private String techReqContent;
	
	public String getTechReqContent() {
		return techReqContent;
	}
	public void setTechReqContent(String techReqContent) {
		this.techReqContent = techReqContent;
	}
	public TechRequireForm() {
		super();
	}
	public String getTechReqTitle() {
		return techReqTitle;
	}
	public void setTechReqTitle(String techReqTitle) {
		this.techReqTitle = techReqTitle;
	}
	public String getTechReqEndTime() {
		return techReqEndTime;
	}
	public void setTechReqEndTime(String techReqEndTime) {
		this.techReqEndTime = techReqEndTime;
	}
	public String getTechReqName() {
		return techReqName;
	}
	public void setTechReqName(String techReqName) {
		this.techReqName = techReqName;
	}
	public String getTechReqMoney() {
		return techReqMoney;
	}
	public void setTechReqMoney(String techReqMoney) {
		this.techReqMoney = techReqMoney;
	}
	public String getTechReqCompany() {
		return techReqCompany;
	}
	public void setTechReqCompany(String techReqCompany) {
		this.techReqCompany = techReqCompany;
	}
	public String getTechReqAddress() {
		return techReqAddress;
	}
	public void setTechReqAddress(String techReqAddress) {
		this.techReqAddress = techReqAddress;
	}
	public String getTechReqPhone() {
		return techReqPhone;
	}
	public void setTechReqPhone(String techReqPhone) {
		this.techReqPhone = techReqPhone;
	}
	
	
	
}
