package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

public interface BlogService {
	BlogPost findOne(Long id);
	BlogPost createBlogPost(BlogPost blogPost);
	List<BlogPost> findAllBlogs();
	List<BlogCategory> findBlogCategories(Long teacher_id);
	BlogCategory findBlogCategory(Long category_id);
	List<BlogPost> findBlogPosts(Long teacher_id);
	BlogPost updateBlogPost(BlogPost blogPost);
	void deleteBlogPost(Long blog_post_id);
	BlogCategory createBlogCategory(BlogCategory blogCategory);
	boolean isBlogCategoryExist(String name, Long teacher_id);
	void deleteBlogCategory(Long blog_category_id);
	BlogCategory renameBlogCategory(BlogCategory blogCategory);
}
