package com.knet51.ccweb.controllers.admin.user;

import org.hibernate.validator.constraints.NotEmpty;

public class RechargeCardForm {
	@NotEmpty
	private String cardid;

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	
}
