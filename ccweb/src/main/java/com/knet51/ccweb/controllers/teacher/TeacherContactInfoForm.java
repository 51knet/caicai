package com.knet51.ccweb.controllers.teacher;

import org.hibernate.validator.constraints.NotEmpty;


public class TeacherContactInfoForm {
	@NotEmpty
	private String address;
	@NotEmpty
	private String cellphone;
	private String phone;
	@NotEmpty
	private String fax;
	@NotEmpty
	private String QQ;
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



	
	public String getQQ() {
		return QQ;
	}
	public void setQQ(String qQ) {
		QQ = qQ;
	}
	public String getMsn() {
		return msn;
	}
	public void setMsn(String msn) {
		this.msn = msn;
	}
}
