package com.knet51.ccweb.jpa.dao;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.blog.BlogCategory;

@Repository
public class BlogCategoryDaoImpl extends CrudImpl<BlogCategory, Long> implements BlogCategoryDao {

	public BlogCategoryDaoImpl() {
		super(BlogCategory.class);
	}
	public BlogCategoryDaoImpl(Class<BlogCategory> domainClass) {
		super(domainClass);
	}
}
