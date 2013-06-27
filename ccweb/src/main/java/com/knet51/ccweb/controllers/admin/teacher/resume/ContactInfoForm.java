package com.knet51.ccweb.controllers.admin.teacher.resume;

import javax.validation.constraints.Size;


public class ContactInfoForm {
	@Size(max=25)
	private String address;
	@Size(max=25)
	private String cellphone;
	@Size(max=25)
	private String phone;
	@Size(max=25)
	private String fax;
	@Size(max=25)
	private String qq;
	@Size(max=25)
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
