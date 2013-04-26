package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogComment;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

public interface BlogService {
	BlogPost findOne(Long id);
	BlogPost createBlogPost(BlogPost blogPost);
	BlogCategory createBlogCategory(BlogCategory blogCategory);
	BlogComment createBlogComment(BlogComment blogComment);
	List<BlogPost> findAllBlogs();
	Page<BlogPost> findAllBlogs(int pageNumber, int pageSize);
	Page<BlogPost> findAllBlogs(int pageNumber, int pageSize, Teacher teacher);
	Page<BlogPost> findAllBlogsNotGarbage(int pageNumber, int pageSize, Teacher teacher);
	Page<BlogPost> findAllBlogsNotGarbageAndNotDraft(int pageNumber, int pageSize, Teacher teacher);
	List<BlogPost> findAllBlogsNotGarbageAndNotDraft(Teacher teacher);
	List<BlogCategory> findBlogCategories(Teacher teacher);
	BlogCategory findBlogCategory(Long category_id);
	List<BlogPost> findBlogPosts(Long teacher_id);
	BlogPost updateBlogPost(BlogPost blogPost);
	void deleteBlogPost(Long blog_post_id);
	boolean isBlogCategoryExist(String name, Teacher teacher);
	BlogCategory renameBlogCategory(BlogCategory blogCategory);
	void deleteBlogCategory(Long blog_category_id);
	Page<BlogPost> findAllBlogsIsGarbage(int pageNumber, int pageSize, Teacher teacher);
	Page<BlogPost> findAllBlogsIsDraft(int pageNumber, int pageSize, Teacher teacher);
	Page<BlogPost> findAllBlogsIsDraftNotGarbage(int pageNumber, int pageSize, Teacher teacher);
	/* use for super admin  */
	Page<BlogPost> findAllBlogsNotGarbageAndNotDraftForSuperAdmin(int pageNumber, int pageSize);
	List<BlogPost> findAllBlogsNotGarbageAndNotDraftForSuperAdmin();
}