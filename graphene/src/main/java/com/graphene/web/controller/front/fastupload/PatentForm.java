package com.graphene.web.controller.front.fastupload;


import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class PatentForm {
	@NotEmpty
	private String patentNum;
	@NotEmpty
	private String patentName;
	@NotEmpty
	private String mainClassNum;
	@NotEmpty
	private String classNum;
	@NotEmpty
	private String applicant;
	@NotEmpty
	private String patentInventer;
	@NotEmpty
	private String publishDate;
	@NotEmpty
	private String publishNum;
	
	private String agency;
	
	private String agent;
	@NotEmpty
	private String applicationDate;
	@NotEmpty
	private String patentAddress;
	@NotEmpty
	private String summary;
	@NotEmpty
	@Size(min=7,max=11)
	private String patentPhone;

	@NotEmpty
	private String patentField;
	
	@Email
	@NotEmpty
	private String patentEmail;
	
		
	public String getPatentEmail() {
		return patentEmail;
	}
	public void setPatentEmail(String patentEmail) {
		this.patentEmail = patentEmail;
	}
	public String getPatentField() {
		return patentField;
	}
	public void setPatentField(String patentField) {
		this.patentField = patentField;
	}
	public String getPatentPhone() {
		return patentPhone;
	}
	public void setPatentPhone(String patentPhone) {
		this.patentPhone = patentPhone;
	}
	public String getPatentNum() {
		return patentNum;
	}
	public void setPatentNum(String patentNum) {
		this.patentNum = patentNum;
	}
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

	public String getPatentInventer() {
		return patentInventer;
	}
	public void setPatentInventer(String patentInventer) {
		this.patentInventer = patentInventer;
	}
	public String getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
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
	public String getApplicationDate() {
		return applicationDate;
	}
	public void setApplicationDate(String applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
	}
	public PatentForm() {
		super();
	}
	public String getPatentAddress() {
		return patentAddress;
	}
	public void setPatentAddress(String patentAddress) {
		this.patentAddress = patentAddress;
	}

	
	
}
