package com.knet51.ccweb.jpa.entities.blog;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;

@Entity
public class BlogComment extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="blogpost_id")
	private BlogPost blog_post;
	private String content;
	private Date date_created;
	private Date date_updated;
	
	public BlogComment(BlogPost blog_post, String content) {
		super();
		this.blog_post = blog_post;
		this.content = content;
		this.date_created  = new Date();
		this.date_updated  = new Date();
	}

	public BlogPost getBlog_post() {
		return blog_post;
	}

	public void setBlog_post(BlogPost blog_post) {
		this.blog_post = blog_post;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDate_created() {
		return date_created;
	}

	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}

	public Date getDate_updated() {
		return date_updated;
	}

	public void setDate_updated(Date date_updated) {
		this.date_updated = date_updated;
	}
}
