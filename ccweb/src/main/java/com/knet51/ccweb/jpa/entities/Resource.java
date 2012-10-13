package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Resource  extends AbstractEntity{
	
	private String name;
	private String savePath;
	private String description;
	private String date;
	@ManyToOne
	private User user;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSavePath() {
		return savePath;
	}
	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Resource() {
		super();
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Resource(String name, String savePath, String description,
			String date) {
		super();
		this.name = name;
		this.savePath = savePath;
		this.description = description;
		this.date = date;
	}
}
