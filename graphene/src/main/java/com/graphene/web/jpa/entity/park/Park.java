package com.graphene.web.jpa.entity.park;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;


import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;

@Entity
public class Park extends AbstractEntity {
	private String parkBoss;
	private String parkPhone;
	private String parkEmail;
	private String parkName;
	private String logoPath;
	@Lob
	private String content;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private User user;



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

	public String getParkName() {
		return parkName;
	}

	public void setParkName(String parkName) {
		this.parkName = parkName;
	}

	public String getLogoPath() {
		return logoPath;
	}

	public void setLogoPath(String logoPath) {
		this.logoPath = logoPath;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Park(User user) {
		this.user = user;
		setId(user.getId());
	}
	
	
	
}
