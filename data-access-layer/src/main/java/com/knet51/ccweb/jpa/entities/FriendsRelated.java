package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class FriendsRelated extends AbstractEntity {
	private Long host_id; 
	private Long follow_id; 
	private int type;
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
	public FriendsRelated() {
		super();
	}
	
	
}
