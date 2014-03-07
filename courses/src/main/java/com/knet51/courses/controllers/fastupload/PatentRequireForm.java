package com.knet51.courses.controllers.fastupload;


import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PatentRequireForm {
	@NotEmpty
	private String patentReqTitle;
	@NotEmpty
	@Email
	private String patentReqEmail;
	
	@NotEmpty
	private String patentReqCooperation;
	@NotEmpty
	private String patentReqMoney;
	@NotEmpty
	private String patentReqField;
	@NotEmpty
	private String patentReqContent;
	
	@NotEmpty
	private String patentReqCompany;
	@NotEmpty
	private String patentReqContact;
	@NotEmpty
	@Size(max=11, min=7)
	private String patentReqPhone;
	@NotEmpty
	private String patentReqFax;

	public String getPatentReqTitle() {
		return patentReqTitle;
	}

	public void setPatentReqTitle(String patentReqTitle) {
		this.patentReqTitle = patentReqTitle;
	}

	public String getPatentReqEmail() {
		return patentReqEmail;
	}

	public void setPatentReqEmail(String patentReqEmail) {
		this.patentReqEmail = patentReqEmail;
	}

	public String getPatentReqCooperation() {
		return patentReqCooperation;
	}

	public void setPatentReqCooperation(String patentReqCooperation) {
		this.patentReqCooperation = patentReqCooperation;
	}

	public String getPatentReqMoney() {
		return patentReqMoney;
	}

	public void setPatentReqMoney(String patentReqMoney) {
		this.patentReqMoney = patentReqMoney;
	}

	public String getPatentReqField() {
		return patentReqField;
	}

	public void setPatentReqField(String patentReqField) {
		this.patentReqField = patentReqField;
	}

	public String getPatentReqContent() {
		return patentReqContent;
	}

	public void setPatentReqContent(String patentReqContent) {
		this.patentReqContent = patentReqContent;
	}

	public String getPatentReqCompany() {
		return patentReqCompany;
	}

	public void setPatentReqCompany(String patentReqCompany) {
		this.patentReqCompany = patentReqCompany;
	}

	public String getPatentReqContact() {
		return patentReqContact;
	}

	public void setPatentReqContact(String patentReqContact) {
		this.patentReqContact = patentReqContact;
	}

	public String getPatentReqPhone() {
		return patentReqPhone;
	}

	public void setPatentReqPhone(String patentReqPhone) {
		this.patentReqPhone = patentReqPhone;
	}

	public String getPatentReqFax() {
		return patentReqFax;
	}

	public void setPatentReqFax(String patentReqFax) {
		this.patentReqFax = patentReqFax;
	}

	public PatentRequireForm() {
		super();
	}

	
	
	
}
