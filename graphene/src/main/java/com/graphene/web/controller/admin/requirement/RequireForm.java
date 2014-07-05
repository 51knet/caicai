package com.graphene.web.controller.admin.requirement;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class RequireForm {
	@NotEmpty
	@Size(min=1,max=200)
	private String title;
	
	private String endTime;
	@NotEmpty
	@Size(min=1,max=200)
	private String name;
	private String money;
	@NotEmpty
	@Size(min=1,max=200)
	private String company;
	@NotEmpty
	@Size(min=1,max=200)
	private String address;
	@NotEmpty
	@Size(min=7,max=12)
	private String phone;
	
	private String content;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public RequireForm() {
		super();
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
