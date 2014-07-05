package com.graphene.web.jpa.entity.applyright;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;


@Entity
public class ParkApplyRight extends AbstractEntity {
	private String parkBoss;
	private String parkPhone;
	private String parkEmail;
	private String parkName;
	@Lob
	private String content;

	private Integer status;

	private Date date;


	@ManyToOne
	private User user;



	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getParkBoss() {
		return parkBoss;
	}

	public void setParkBoss(String parkBoss) {
		this.parkBoss = parkBoss;
	}

	public String getParkPhone() {
		return parkPhone;
	}

	public void setParkPhone(String parkPhone) {
		this.parkPhone = parkPhone;
	}

	public String getParkEmail() {
		return parkEmail;
	}

	public void setParkEmail(String parkEmail) {
		this.parkEmail = parkEmail;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}


	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ParkApplyRight() {
		super();
	}


	
}
