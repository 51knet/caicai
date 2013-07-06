package com.knet51.ccweb.jpa.entities.timeline;


import java.util.Date;

import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class LegacyTrends extends AbstractEntity {

	private Long userId; // user's id, DO NOT create any foreign key
	private Long itemId; // save the items' id such as:courseid,resourcesId and so on;
	private String variety;//save the items' such as:course,resources and so on;
	private String title;
	private String coverUrl;
	private String email;
	private String context; // limited to 400 chars
	private String name;
	private String gender;
	private String role;
	private String forbidden;
	private Date publishDate;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getCoverUrl() {
		return coverUrl;
	}
	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getForbidden() {
		return forbidden;
	}
	public void setForbidden(String forbidden) {
		this.forbidden = forbidden;
	}
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
}
