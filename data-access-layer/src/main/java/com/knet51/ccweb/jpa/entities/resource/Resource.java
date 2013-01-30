package com.knet51.ccweb.jpa.entities.resource;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
@Table(name="resource_remove")
public class Resource  extends AbstractEntity{
	
	private String name;
	private String saveName;
	private String savePath;
	@Lob
	@Column(length=10000)
	private String description;
	private String date;
	private Integer status;
	@ManyToOne
	private User user;
	
	@ManyToOne
	private ResourceType resourceType;

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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
	
	
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public ResourceType getResourceType() {
		return resourceType;
	}
	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}
	public Resource(String name, String savePath, String description,
			String date, Integer status) {
		super();
		this.name = name;
		this.savePath = savePath;
		this.description = description;
		this.date = date;
		this.status = status;
		
	}

}
