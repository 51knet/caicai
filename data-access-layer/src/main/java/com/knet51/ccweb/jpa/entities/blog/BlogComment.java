package com.knet51.ccweb.jpa.entities.blog;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class BlogComment extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="blogpost_id")
	private BlogPost blogPost;
	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher author;
	@Lob
	private String content;
	private Date dateCreated;
	private Date dateUpdated;
	
	public BlogComment() {
		this.dateCreated  = new Date();
		this.dateUpdated  = new Date();
	}
	
	public BlogComment(BlogPost blogPost, String content) {
		super();
		this.blogPost = blogPost;
		this.content = content;
		this.dateCreated  = new Date();
		this.dateUpdated  = new Date();
	}

	public BlogPost getBlogPost() {
		return blogPost;
	}

	public void setBlogPost(BlogPost blogPost) {
		this.blogPost = blogPost;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	public Date getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	public Teacher getAuthor() {
		return author;
	}

	public void setAuthor(Teacher author) {
		this.author = author;
	}

	
}
