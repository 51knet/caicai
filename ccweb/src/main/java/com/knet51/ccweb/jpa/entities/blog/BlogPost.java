package com.knet51.ccweb.jpa.entities.blog;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
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

	@OneToMany(mappedBy="blog_post", fetch=FetchType.EAGER)
	private Collection<BlogComment> blogComments;
	
	@NotEmpty
	private String title;
	@NotEmpty
	private String content;
	private Date date_created;
	private Date date_updated;
	
	public BlogPost() {
		this.date_created  = new Date();
		this.date_updated  = new Date();
	}
	
	public BlogPost(Teacher author, BlogCategory blogCategory, String title,
			String content) {
		super();
		this.author = author;
		this.blogCategory = blogCategory;
		this.title = title;
		this.content = content;
		this.date_created  = new Date();
		this.date_updated  = new Date();
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
	@Override
	public String toString() {
		return "BlogPost [author=" + author + ", blogCategory=" + blogCategory
				+ ", blogComments=" + blogComments + ", title=" + title
				+ ", content=" + content + ", date_created=" + date_created
				+ ", date_updated=" + date_updated + "]";
	}



}