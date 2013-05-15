package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class RechargeHistory extends AbstractEntity {
	private Long cardid;
	private Long userid;
	private String userName;
	private Long price;
	private Date date;
	public Long getCardid() {
		return cardid;
	}
	public void setCardid(Long cardid) {
		this.cardid = cardid;
	}
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public RechargeHistory() {
		super();
	}
	
}
