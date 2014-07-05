package com.graphene.web.jpa.entity.announcement;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class AnnoPhoto  {
	@Id
	private Long id;
	private Long userid;
	private String photourl;
	
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
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
	public AnnoPhoto(Announcement announcement){
		this.announcement = announcement;
		setId(announcement.getId());
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
