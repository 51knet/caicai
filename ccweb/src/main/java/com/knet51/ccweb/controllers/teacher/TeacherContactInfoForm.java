package com.knet51.ccweb.controllers.teacher;

import javax.validation.constraints.Size;


public class TeacherContactInfoForm {
	@Size(min=3,max=25)
	private String address;
	@Size(min=3,max=25)
	private String cellphone;
	@Size(min=3,max=25)
	private String phone;
	@Size(min=3,max=25)
	private String fax;
	@Size(min=3,max=25)
	private String qq;
	@Size(min=3,max=25)
	private String msn;
	
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getCellphone() {
		return cellphone;
	}
	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
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
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
}
