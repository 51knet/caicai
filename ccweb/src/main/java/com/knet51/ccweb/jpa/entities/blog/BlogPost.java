package com.knet51.ccweb.jpa.entities.blog;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	@OneToMany(mappedBy="blog_post")
	private Collection<BlogComment> blogComments;
	
	private String title;
	private String content;
	private Date date_created;
	private Date date_updated;



}