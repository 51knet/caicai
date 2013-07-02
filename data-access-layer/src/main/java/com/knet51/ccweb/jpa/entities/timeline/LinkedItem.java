package com.knet51.ccweb.jpa.entities.timeline;


import javax.persistence.Entity;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class LinkedItem extends AbstractEntity {

	private String variety; // linked item variety name: Course, Announcement, Blog, Resources, Resume etc.
	private Long itemId; // linked item id, meaning for foreign key with other tables, but DO NOT create foreign key.
	public String getVariety() {
		return variety;
	}
	public void setVariety(String variety) {
		this.variety = variety;
	}
	public Long getItemId() {
		return itemId;
	}
	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}
	
}
