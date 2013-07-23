package com.knet51.ccweb.jpa.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
@Entity
public class Comment extends AbstractEntity {
	@ManyToOne
	private User user;
	@Lob
	private String context;
	private Long trendId;
	@ManyToOne
	private User host;
	private Date publishDate;
	

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
	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public User getHost() {
		return host;
	}
	public void setHost(User host) {
		this.host = host;
	}

	

}
