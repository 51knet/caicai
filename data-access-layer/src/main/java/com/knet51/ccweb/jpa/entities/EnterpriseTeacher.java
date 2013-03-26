package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
@Entity
@Table(name="enterprise_teacher")
public class EnterpriseTeacher extends AbstractEntity {
	private String photourl;
	@Lob
	private String content;
	
	@ManyToOne
	private User user;

	public String getPhotourl() {
		return photourl;
	}

	public void setPhotourl(String photourl) {
		this.photourl = photourl;
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

	public EnterpriseTeacher() {
		super();
	}
	
	
}
