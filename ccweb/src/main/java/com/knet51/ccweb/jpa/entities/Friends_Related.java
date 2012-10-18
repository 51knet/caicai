package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class Friends_Related extends AbstractEntity {
	private Long host_id; //被响应者
	private Long follow_id; //发起响应者
	private int type;// 发送请求为1，同意为2，0为不同意或者忽视
	public Long getHost_id() {
		return host_id;
	}
	public void setHost_id(Long host_id) {
		this.host_id = host_id;
	}
	public Long getFollow_id() {
		return follow_id;
	}
	public void setFollow_id(Long follow_id) {
		this.follow_id = follow_id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Friends_Related() {
		super();
	}
	
	
}
