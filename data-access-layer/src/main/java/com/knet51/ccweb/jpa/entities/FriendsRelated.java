package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;

@Entity
public class FriendsRelated extends AbstractEntity {
	private Long host_id; 
	private Long follow_id; 
	private String group;
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

	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public FriendsRelated() {
		super();
	}
	
	
}
