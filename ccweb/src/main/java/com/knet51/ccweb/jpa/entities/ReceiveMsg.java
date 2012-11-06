package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ReceiveMsg extends AbstractEntity {
	
	private Integer readed;
	private Integer deled;
	@ManyToOne
	private User user;
	@ManyToOne
	private SendMsg sendMsg;

	public User getUser() {
		return user;
	}
	public Integer getReaded() {
		return readed;
	}
	public void setReaded(Integer readed) {
		this.readed = readed;
	}
	public Integer getDeled() {
		return deled;
	}
	public void setDeled(Integer deled) {
		this.deled = deled;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public SendMsg getSendMsg() {
		return sendMsg;
	}
	public void setSendMsg(SendMsg sendMsg) {
		this.sendMsg = sendMsg;
	}
	public ReceiveMsg() {
		super();
	}
	
	
}
