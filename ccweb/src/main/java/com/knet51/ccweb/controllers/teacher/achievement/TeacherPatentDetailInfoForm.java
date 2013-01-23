package com.knet51.ccweb.controllers.teacher.achievement;

import org.hibernate.validator.constraints.NotEmpty;

public class TeacherPatentDetailInfoForm {
	@NotEmpty
	private String inventer;
	@NotEmpty
	private String patentName;
	@NotEmpty
	private String patentType;
	@NotEmpty
	private String number;
	private String patentDesc;
	public String getInventer() {
		return inventer;
	}
	public void setInventer(String inventer) {
		this.inventer = inventer;
	}
	
	public String getPatentName() {
		return patentName;
	}
	public void setPatentName(String patentName) {
		this.patentName = patentName;
	}
	public String getPatentType() {
		return patentType;
	}
	public void setPatentType(String patentType) {
		this.patentType = patentType;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getPatentDesc() {
		return patentDesc;
	}
	public void setPatentDesc(String patentDesc) {
		this.patentDesc = patentDesc;
	}
	public TeacherPatentDetailInfoForm() {
		super();
	}
	
	
}
