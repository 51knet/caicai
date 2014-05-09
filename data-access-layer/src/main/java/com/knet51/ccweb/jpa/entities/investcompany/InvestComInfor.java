package com.knet51.ccweb.jpa.entities.investcompany;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class InvestComInfor extends AbstractEntity {
	private String investRange;
	private String investField;
	@Lob
	private String companyinfo;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public InvestComInfor() {
		super();
	}


	
	
}
