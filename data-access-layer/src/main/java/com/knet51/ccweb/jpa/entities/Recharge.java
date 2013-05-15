package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;


@Entity
public class Recharge extends AbstractEntity {
	private Long price;
	private Date date;
	private String cardid;
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Recharge() {
		super();
	}
	
}
