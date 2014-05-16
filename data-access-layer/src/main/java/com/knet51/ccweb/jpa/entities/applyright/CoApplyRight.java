package com.knet51.ccweb.jpa.entities.applyright;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.User;

@Entity
public class CoApplyRight extends AbstractEntity {
	private String boss;
	private String bossPhone;
	@Lob
	private String content;
	private String orgCodePath;
	private String bizLicPath;
	private Integer status;
	private String comApplypermit;
	private Date date;
	@Column(name="Boss_ID")
	private String bossId;

	@ManyToOne
	private User user;

	public String getBoss() {
		return boss;
	}

	public void setBoss(String boss) {
		this.boss = boss;
	}

	public String getBossPhone() {
		return bossPhone;
	}

	public void setBossPhone(String bossPhone) {
		this.bossPhone = bossPhone;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getOrgCodePath() {
		return orgCodePath;
	}

	public void setOrgCodePath(String orgCodePath) {
		this.orgCodePath = orgCodePath;
	}

	public String getBizLicPath() {
		return bizLicPath;
	}

	public void setBizLicPath(String bizLicPath) {
		this.bizLicPath = bizLicPath;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getComApplypermit() {
		return comApplypermit;
	}

	public void setComApplypermit(String comApplypermit) {
		this.comApplypermit = comApplypermit;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getBossId() {
		return bossId;
	}

	public void setBossId(String bossId) {
		this.bossId = bossId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public CoApplyRight() {
		super();
	}
	
	
}
