package com.graphene.web.jpa.entity.patent;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;

@Entity
public class Patent extends AbstractEntity{
	private String patentNum; 
	private String patentName;
	private String mainClassNum;
	private String classNum;
	private String applicant;
	private String inventer;
	private String publishDate;
	private String publishNum;
	private String agency;
	private String agent;
	private String applicationDate;
	private String address;
	private String phone;
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	private Long count;
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	@Lob
	private String summary;
	
	private Integer status;// store or publish
	
	private Integer focus; // show it in home page or not
	
	private Integer country;// 1 foreign; 0 chinese;
	
	@ManyToOne
	private PatentType patentType;
	
	@ManyToOne
	private User user;
	
	private String patentField;
	
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getMainClassNum() {
		return mainClassNum;
	}
	public void setMainClassNum(String mainClassNum) {
		this.mainClassNum = mainClassNum;
	}
	public String getClassNum() {
		return classNum;
	}
	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}
	public String getApplicant() {
		return applicant;
	}
	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}
	public String getInventer() {
		return inventer;
	}
	public void setInventer(String inventer) {
		this.inventer = inventer;
	}
	public String getPublishNum() {
		return publishNum;
	}
	public void setPublishNum(String publishNum) {
		this.publishNum = publishNum;
	}
	public String getAgency() {
		return agency;
	}
	public void setAgency(String agency) {
		this.agency = agency;
	}
	public String getAgent() {
		return agent;
	}
	public void setAgent(String agent) {
		this.agent = agent;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}


	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public String getPatentNum() {
		return patentNum;
	}
	public void setPatentNum(String patentNum) {
		this.patentNum = patentNum;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public PatentType getPatentType() {
		return patentType;
	}
	public void setPatentType(PatentType patentType) {
		this.patentType = patentType;
	}
	public String getPatentField() {
		return patentField;
	}
	public void setPatentField(String patentField) {
		this.patentField = patentField;
	}
	public Patent() {
		super();
	}
	public Integer getFocus() {
		return focus;
	}
	public void setFocus(Integer focus) {
		this.focus = focus;
	}
	public Integer getCountry() {
		return country;
	}
	public void setCountry(Integer country) {
		this.country = country;
	}
	
	
}
