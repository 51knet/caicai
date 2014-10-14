package com.knet51.diplomat.controllers.admin.investcompany;

import org.hibernate.validator.constraints.NotEmpty;


public class InvestSuccessCaseForm {
	@NotEmpty
	private String name;
	@NotEmpty
	private String money;
	@NotEmpty
	private String field;
	@NotEmpty
	private String date;
	@NotEmpty
	private String content;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMoney() {
		return money;
	}
	public void setMoney(String money) {
		this.money = money;
	}
	public String getField() {
		return field;
	}
	public void setField(String field) {
		this.field = field;
	}
	
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	protected InvestSuccessCaseForm() {
		super();
	}
	
	
}
