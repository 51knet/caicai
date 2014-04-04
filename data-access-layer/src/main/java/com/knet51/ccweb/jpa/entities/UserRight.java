package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usr_right")
public class UserRight extends AbstractEntity {
	private String userRight;
	@ManyToOne
	private User user;
	public String getUserRight() {
		return userRight;
	}
	public void setUserRight(String userRight) {
		this.userRight = userRight;
	}
	public User getUser() {
		return user;
	}
	public UserRight() {
		super();
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
