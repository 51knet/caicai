package com.knet51.courses.controllers.patent;

public class DetailSearchPatentForm {
	private String patentNum;
	private String patentName;
	private String mainClassNum;
	private String classNum;
	private String applicant;
	private String inventer;
	private String publishNum;
	private String patentField;
	
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
	
	public DetailSearchPatentForm() {
		super();
	}
	public String getPatentField() {
		return patentField;
	}
	public void setPatentField(String patentField) {
		this.patentField = patentField;
	}

	
	
}
