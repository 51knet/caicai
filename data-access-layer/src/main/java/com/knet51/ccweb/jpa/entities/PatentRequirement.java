package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.patent.PatentType;

@Entity
public class PatentRequirement extends AbstractEntity {
	private String requirementName;
	private String cooperation;
	private String money;
	private Date date; 
	private String requirementField;
	@Lob
	private String content;
	
	private String company;
	private String contact;
	private String phone;
	private String fax;
	private String email;
	
	private Integer status; // 0:waite; 1:pass.
	
	@ManyToOne
	private User user;
	@ManyToOne
	private PatentType patentType;
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
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public PatentType getPatentType() {
		return patentType;
	}
	public void setPatentType(PatentType patentType) {
		this.patentType = patentType;
	}
	public PatentRequirement() {
		super();
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
}
