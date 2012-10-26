package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class ReceiveMsg extends AbstractEntity {
	
	private Integer isRead;
	private Integer isDele;
	@ManyToOne
	private User user;
	@ManyToOne
	private SendMsg sendMsg;
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public Integer getIsDele() {
		return isDele;
	}
	public void setIsDele(Integer isDele) {
		this.isDele = isDele;
	}
	public User getUser() {
		return user;
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
