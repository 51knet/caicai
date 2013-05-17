package com.knet51.ccweb.controllers.admin.enterprise;

import org.hibernate.validator.constraints.NotEmpty;

public class WithdrawsApplyForm {
	@NotEmpty
	private String sum;
	@NotEmpty
	private String content;
	public String getSum() {
		return sum;
	}
	public void setSum(String sum) {
		this.sum = sum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
