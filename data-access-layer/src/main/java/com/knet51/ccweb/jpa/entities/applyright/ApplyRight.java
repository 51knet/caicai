package com.knet51.ccweb.jpa.entities.applyright;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Version;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;
@Entity
public class ApplyRight extends AbstractEntity {
	private String name;
	private String phone;
	@Lob
	private String content;
	private String savePath;
	private Integer status;
	private String applypermit;
	private Date date;
	@Column(name="ID_NUM")
	private String idNum;

	@ManyToOne(fetch = FetchType.LAZY)
	private User user;
	

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

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

	public ApplyRight() {
		super();
	}
	
	
}
