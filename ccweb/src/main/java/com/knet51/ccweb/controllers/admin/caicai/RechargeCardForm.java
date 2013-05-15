package com.knet51.ccweb.controllers.admin.caicai;

import org.hibernate.validator.constraints.NotEmpty;

public class RechargeCardForm {
	@NotEmpty
	private String cardid;
	
	@NotEmpty
	private String price;
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	
	
}
