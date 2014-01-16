package com.knet51.patents.controllers.admin.requirement;

import java.util.Date;

import javax.validation.constraints.Size;


import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PatentRequirementForm {
	@NotEmpty
	private String requirementName;
	@NotEmpty
	@Email
	private String email;
	
	@NotEmpty
	private String cooperation;
	@NotEmpty
	private String money;

	private String requirementField;
	
	private String content;
	
	@NotEmpty
	private String company;
	@NotEmpty
	private String contact;
	@NotEmpty
	@Size(max=11, min=7)
	private String phone;
	
	private String fax;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public PatentRequirementForm() {
		super();
	}

	public String getRequirementName() {
		return requirementName;
	}

	public void setRequirementName(String requirementName) {
		this.requirementName = requirementName;
	}

	public String getCooperation() {
		return cooperation;
	}

	public void setCooperation(String cooperation) {
		this.cooperation = cooperation;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getRequirementField() {
		return requirementField;
	}

	public void setRequirementField(String requirementField) {
		this.requirementField = requirementField;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}
	
	
}
