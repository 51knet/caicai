package com.graphene.web.jpa.entity.allies;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.graphene.web.jpa.entity.AbstractEntity;
import com.graphene.web.jpa.entity.user.User;
@Entity
public class Allies extends AbstractEntity {
	private String alliesBoss;
	private String alliesPhone;
	private String alliesEmail;
	private String alliesName;
	private String logoPath;
	@Lob
	private String content;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name="id")
	private User user;

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

	public String getAlliesName() {
		return alliesName;
	}

	public void setAlliesName(String alliesName) {
		this.alliesName = alliesName;
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

	public Allies(User user) {
		super();
		this.user = user;
		setId(user.getId());
	}
	
	
}
