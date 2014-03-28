package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
@Entity
public class RoleValid extends AbstractEntity {
	private String name;
	private String email;
	private String phone;
	@Lob
	private String content;
	private String savePath;
	private Integer status;
	private String investor;
	private String ledinvestor;
	private String investcompany;
	private String Incubation;
	@ManyToOne
	private User user;
	
	
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getInvestor() {
		return investor;
	}

	public void setInvestor(String investor) {
		this.investor = investor;
	}

	public String getLedinvestor() {
		return ledinvestor;
	}

	public void setLedinvestor(String ledinvestor) {
		this.ledinvestor = ledinvestor;
	}

	public String getInvestcompany() {
		return investcompany;
	}

	public void setInvestcompany(String investcompany) {
		this.investcompany = investcompany;
	}

	public String getIncubation() {
		return Incubation;
	}

	public void setIncubation(String incubation) {
		Incubation = incubation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoleValid() {
		super();
	}
	
	
}
