package com.knet51.patents.controllers.admin.applyright;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;


public class ApplyRightForm {
	@NotEmpty
	@Size(min=1,max=200)
	private String name;
	@NotEmpty
	@Size(min=7,max=11)
	private String phone;
	private String content;
	@NotEmpty
	@Size(min=18, max=18)
	private String idNum;
	
	private String applypermit;
	
	
	
	public String getApplypermit() {
		return applypermit;
	}
	public void setApplypermit(String applypermit) {
		this.applypermit = applypermit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public ApplyRightForm() {
		super();
	}
	public String getIdNum() {
		return idNum;
	}
	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}
	
	
}
