package com.knet51.ccweb.jpa.entities.investcompany;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class InvestSuccessCase extends AbstractEntity {
	private String name;
	private String money;
	private String field;
	private String date;
	@Lob
	private String content;
	
	@ManyToOne
	private User user;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public InvestSuccessCase() {
		super();
	}
	
	
}
