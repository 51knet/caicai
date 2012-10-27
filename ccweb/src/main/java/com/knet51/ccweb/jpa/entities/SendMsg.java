package com.knet51.ccweb.jpa.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class SendMsg extends AbstractEntity {
	private String title;
	private String content;
	private String date;
	private Integer isDelete;  //  1: 没有删除   2：已经删除
	
	@OneToMany(mappedBy="sendMsg")
	private Set<ReceiveMsg>  receiveMsg = new HashSet<ReceiveMsg>();
	
	@ManyToOne
	private User user;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Set<ReceiveMsg> getReceiveMsg() {
		return receiveMsg;
	}

	public void setReceiveMsg(Set<ReceiveMsg> receiveMsg) {
		this.receiveMsg = receiveMsg;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}

	public SendMsg() {
		super();
	}
	
	
}
