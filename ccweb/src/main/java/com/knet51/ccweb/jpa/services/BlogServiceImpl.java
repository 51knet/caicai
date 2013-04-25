package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogComment;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.repository.BlogCategoryRepository;
import com.knet51.ccweb.jpa.repository.BlogCommentRepository;
import com.knet51.ccweb.jpa.repository.BlogPostRepository;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	@Autowired
	private BlogCategoryRepository blogCategoryRepository;
	@Autowired
	private BlogCommentRepository blogCommentRepository;
	
	
	@Override
	public BlogPost findOne(Long id) {
		return blogPostRepository.findOne(id);
	}

	@Override
	public List<BlogPost> findAllBlogs() {
		return blogPostRepository.findAll();
	}

	@Override
	public Page<BlogPost> findAllBlogs(int pageNumber, int pageSize) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findAll(dateDesc);
		return onePage;
	}

	@Override
	public Page<BlogPost> findAllBlogs(int pageNumber, int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndForbiddenIsNull(teacher, dateDesc);
		return onePage;
	}
	@Override
	public Page<BlogPost> findAllBlogsNotGarbage(int pageNumber, int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndGarbageAndForbiddenIsNull(teacher, false, dateDesc);
		return onePage;
	}
	@Override
	public Page<BlogPost> findAllBlogsNotGarbageAndNotDraft(int pageNumber,
			int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndGarbageAndDraftAndForbiddenIsNull(teacher, false, false, dateDesc);
		return onePage;
	}

	@Override
	public Page<BlogPost> findAllBlogsIsGarbage(int pageNumber, int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndGarbageAndForbiddenIsNull(teacher, true, dateDesc);
		return onePage;
	}
	@Override
	public Page<BlogPost> findAllBlogsIsDraft(int pageNumber, int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndDraftAndForbiddenIsNull(teacher, true, dateDesc);
		return onePage;
	}
	@Override
	public Page<BlogPost> findAllBlogsIsDraftNotGarbage(int pageNumber, int pageSize, Teacher teacher) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<BlogPost> onePage = blogPostRepository.findByAuthorAndGarbageAndDraftAndForbiddenIsNull(teacher, false, true, dateDesc);
		return onePage;
	}
	@Override
	public BlogPost createBlogPost(BlogPost blogPost) {
		return blogPostRepository.save(blogPost);
	}

	@Override
	public List<BlogCategory> findBlogCategories(Teacher teacher) {
		return blogCategoryRepository.findByAuthor(teacher);
	}

	@Override
	public BlogCategory findBlogCategory(Long category_id) {
		return blogCategoryRepository.findOne(category_id);
	}

	@Override
	public List<BlogPost> findBlogPosts(Long teacher_id) {
		return  blogPostRepository.findAll();
	}

	@Override
	public BlogPost updateBlogPost(BlogPost blogPost) {
		return blogPostRepository.save(blogPost);
	}

	@Override
	public void deleteBlogPost(Long blog_post_id) {
	}

	@Override
	public BlogCategory createBlogCategory(BlogCategory blogCategory) {
		return blogCategoryRepository.save(blogCategory);
	}

	@Override
	public BlogComment createBlogComment(BlogComment blogComment) {
		return blogCommentRepository.save(blogComment);
	}

	@Override
	public boolean isBlogCategoryExist(String name, Teacher teacher) {
		return (blogCategoryRepository.findByNameAndTeacher(name, teacher) != null );
	}

	@Override
	public void deleteBlogCategory(Long blog_category_id) {
		blogCategoryRepository.delete(blog_category_id);
	}

	@Override
	public BlogCategory renameBlogCategory(BlogCategory blogCategory) {
		return blogCategoryRepository.save(blogCategory);
	}

	@Override
	public List<BlogPost> findAllBlogsNotGarbageAndNotDraft(Teacher teacher) {
		
		return blogPostRepository.findByAuthorAndGarbageAndDraft(teacher, false, false);
	}


	

	

	

}
