package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Entity
public class AnnoPhoto extends AbstractEntity  {
	private Long userid;
	private String photourl;
	@OneToOne
	private Announcement announcement;
	public Long getUserid() {
		return userid;
	}
	public void setUserid(Long userid) {
		this.userid = userid;
	}
	public String getPhotourl() {
		return photourl;
	}
	public void setPhotourl(String photourl) {
		this.photourl = photourl;
	}
	public Announcement getAnnouncement() {
		return announcement;
	}
	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}
	public AnnoPhoto() {
		super();
	}
	
	
}
