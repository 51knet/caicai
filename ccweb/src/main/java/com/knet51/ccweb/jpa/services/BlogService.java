package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.blog.BlogPost;

public interface BlogService {
	BlogPost findOne(Long id);
	List<BlogPost> findAllBlogs();
}
