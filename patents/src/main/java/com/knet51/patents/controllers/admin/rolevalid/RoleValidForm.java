package com.knet51.patents.controllers.admin.rolevalid;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class RoleValidForm {
	@NotEmpty
	@Size(min=1,max=200)
	private String name;
	@NotEmpty
	@Email
	@Size(min=1,max=200)
	private String email;
	@NotEmpty
	@Size(min=7,max=11)
	private String phone;
	private String content;
	
	private String investor;
	private String ledinvestor;
	private String investcompany;
	private String Incubation;
	
	
	public String getInvestor() {
		return investor;
	}
	public void setInvestor(String investor) {
		this.investor = investor;
	}
	public String getLedinvestor() {
		return ledinvestor;
	}
	public void setLedinvestor(String ledinvestor) {
		this.ledinvestor = ledinvestor;
	}
	public String getInvestcompany() {
		return investcompany;
	}
	public void setInvestcompany(String investcompany) {
		this.investcompany = investcompany;
	}
	public String getIncubation() {
		return Incubation;
	}
	public void setIncubation(String incubation) {
		Incubation = incubation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RoleValidForm() {
		super();
	}
	
	
}
