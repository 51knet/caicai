package com.knet51.ccweb.jpa.entities.timeline;


import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class Trends extends AbstractEntity {

	private Long userId; // user's id, DO NOT create any foreign key
	@OneToOne
	@PrimaryKeyJoinColumn(name = "id")
	private LinkedItem linkedItem;  // linked item, foreign key with LinkedItem table, temporary solution.
	private String email;
	private String context; // limited to 400 chars
	private String name;
	private String gender;
	private String role;
	private String forbidden;
	private Long banner_id;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public LinkedItem getLinkedItem() {
		return linkedItem;
	}
	public void setLinkedItem(LinkedItem linkedItem) {
		this.linkedItem = linkedItem;
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
	public Long getBanner_id() {
		return banner_id;
	}
	public void setBanner_id(Long banner_id) {
		this.banner_id = banner_id;
	}
}
