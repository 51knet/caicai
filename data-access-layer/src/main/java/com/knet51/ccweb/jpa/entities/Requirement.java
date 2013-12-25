package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

@Entity
public class Requirement extends AbstractEntity {
	private String title;
	@Lob
	private String content;
	private Date date; 
	private String endTime;
	private String name;
	private String money;
	private String address;
	private String company;
	private String phone;
	private Integer status; // 0:waite; 1:pass.
	
	@ManyToOne
	private User user;
	
	@ManyToOne
	private RequirType requirType;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user; 
	}

	public Requirement() {
		super();
	}

	public RequirType getRequirType() {
		return requirType;
	}

	public void setRequirType(RequirType requirType) {
		this.requirType = requirType;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
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
	public void setMoney(String money) {
		this.money = money;
	}
	
	public String getMoney() {
		return money;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}




	
}
