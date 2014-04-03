package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
@Entity
public class RoleValid extends AbstractEntity {
	private String name;
	private String phone;
	@Lob
	private String content;
	private String savePath;
	private Integer status;
	private String applypermit;
	private Date date;
	@Version
	private Integer version;

	@ManyToOne
	private User user;
	
	
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getApplypermit() {
		return applypermit;
	}

	public void setApplypermit(String applypermit) {
		this.applypermit = applypermit;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public RoleValid() {
		super();
	}
	
	
}
