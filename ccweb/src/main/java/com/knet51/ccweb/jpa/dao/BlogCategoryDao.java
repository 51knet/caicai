package com.knet51.ccweb.jpa.dao;

import com.knet51.ccweb.jpa.entities.blog.BlogCategory;

public interface BlogCategoryDao extends Crud<BlogCategory, Long> {
	BlogCategory findByNameAndTeacherId(String name, Long teacher_id);
}
