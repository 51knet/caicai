package com.knet51.ccweb.jpa.dao;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.blog.BlogPost;

@Repository
public class BlogDaoImpl extends CrudImpl<BlogPost, Long> implements BlogDao {

	public BlogDaoImpl() {
		super(BlogPost.class);
	}
	public BlogDaoImpl(Class<BlogPost> domainClass) {
		super(domainClass);
	}
}
