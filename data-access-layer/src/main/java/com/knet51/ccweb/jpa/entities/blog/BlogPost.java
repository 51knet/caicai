package com.knet51.ccweb.jpa.entities.blog;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotEmpty;

import com.knet51.ccweb.jpa.entities.AbstractEntity;
import com.knet51.ccweb.jpa.entities.Teacher;

@Entity
public class BlogPost extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name="teacher_id")
	private Teacher author;

	@ManyToOne
	@JoinColumn(name="blogcategory_id")
	private BlogCategory blogCategory;

	@OneToMany(mappedBy="blogPost", fetch=FetchType.EAGER)
	private Collection<BlogComment> blogComments;
	
	@NotEmpty
	private String title;
	
	@Lob
	@NotEmpty
	private String content;
	private Date dateCreated;
	private Date dateUpdated;
	private boolean draft = false; // by default all blogPosts are not draft
	private boolean garbage = false; // by default blogPost is not in garbageCan
	
	public BlogPost() {
		this.dateCreated  = new Date();
		this.dateUpdated  = new Date();
	}
	
	public BlogPost(Teacher author, BlogCategory blogCategory, String title,
			String content) {
		super();
		this.author = author;
		this.blogCategory = blogCategory;
		this.title = title;
		this.content = content;
		this.dateCreated  = new Date();
		this.dateUpdated  = new Date();
	}
	public Teacher getAuthor() {
		return author;
	}
	public void setAuthor(Teacher author) {
		this.author = author;
	}
	public BlogCategory getBlogCategory() {
		return blogCategory;
	}
	public void setBlogCategory(BlogCategory blogCategory) {
		this.blogCategory = blogCategory;
	}
	public Collection<BlogComment> getBlogComments() {
		return blogComments;
	}
	public void setBlogComments(Collection<BlogComment> blogComments) {
		this.blogComments = blogComments;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	@Override
	public String toString() {
		return "BlogPost [author=" + author + ", blogCategory=" + blogCategory
				+ ", blogComments=" + blogComments + ", title=" + title
				+ ", content=" + content + ", dateCreated=" + dateCreated
				+ ", dateUpdated=" + dateUpdated + "]";
	}

	public boolean isDraft() {
		return draft;
	}

	public void setDraft(boolean draft) {
		this.draft = draft;
	}

	public boolean isGarbage() {
		return garbage;
	}

	public void setGarbage(boolean garbage) {
		this.garbage = garbage;
	}
	

}