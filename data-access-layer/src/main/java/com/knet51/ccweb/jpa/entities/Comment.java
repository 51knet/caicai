package com.knet51.ccweb.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.Lob;
@Entity
public class Comment extends AbstractEntity {
	private Long userId;
	private String name;
	private String photo_url;
	@Lob
	private String context;
	private Long trendId;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoto_url() {
		return photo_url;
	}
	public void setPhoto_url(String photo_url) {
		this.photo_url = photo_url;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public Long getTrendId() {
		return trendId;
	}
	public void setTrendId(Long trendId) {
		this.trendId = trendId;
	}
	public Comment() {
		super();
	}
	
	

}
