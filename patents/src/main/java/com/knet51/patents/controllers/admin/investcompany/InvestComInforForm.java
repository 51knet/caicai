package com.knet51.patents.controllers.admin.investcompany;

import org.hibernate.validator.constraints.NotEmpty;

public class InvestComInforForm {
	@NotEmpty
	private String investRange;
	@NotEmpty
	private String investField;
	@NotEmpty
	private String companyinfo;
	public String getInvestRange() {
		return investRange;
	}
	public void setInvestRange(String investRange) {
		this.investRange = investRange;
	}
	public String getInvestField() {
		return investField;
	}
	public void setInvestField(String investField) {
		this.investField = investField;
	}
	public String getCompanyinfo() {
		return companyinfo;
	}
	public void setCompanyinfo(String companyinfo) {
		this.companyinfo = companyinfo;
	}
	protected InvestComInforForm() {
		super();
	}
	
	
}
