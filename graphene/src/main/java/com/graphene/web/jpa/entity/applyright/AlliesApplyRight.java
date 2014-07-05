package com.graphene.web.jpa.entity.applyright;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;

@Entity
public class AlliesApplyRight extends AbstractEntity {
	private String alliesName;
	private String alliesBoss;
	private String alliesPhone;
	private String alliesEmail;
	private String alliesFile;
	@Lob
	private String alliesContent;
	private Integer status;
	private Date date;

	@ManyToOne
	private User user;
	
	

	public String getAlliesName() {
		return alliesName;
	}

	public void setAlliesName(String alliesName) {
		this.alliesName = alliesName;
	}

	public String getAlliesBoss() {
		return alliesBoss;
	}

	public void setAlliesBoss(String alliesBoss) {
		this.alliesBoss = alliesBoss;
	}

	public String getAlliesPhone() {
		return alliesPhone;
	}

	public void setAlliesPhone(String alliesPhone) {
		this.alliesPhone = alliesPhone;
	}

	public String getAlliesEmail() {
		return alliesEmail;
	}

	public void setAlliesEmail(String alliesEmail) {
		this.alliesEmail = alliesEmail;
	}

	public String getAlliesFile() {
		return alliesFile;
	}

	public void setAlliesFile(String alliesFile) {
		this.alliesFile = alliesFile;
	}

	public String getAlliesContent() {
		return alliesContent;
	}

	public void setAlliesContent(String alliesContent) {
		this.alliesContent = alliesContent;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public AlliesApplyRight() {
		super();
	}
	
	
}
