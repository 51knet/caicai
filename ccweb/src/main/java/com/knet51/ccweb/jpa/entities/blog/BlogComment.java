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
	
	private Date date_created;
	private Date date_updated;
}
