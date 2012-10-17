package com.knet51.ccweb.jpa.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.BlogCategoryDao;
import com.knet51.ccweb.jpa.entities.blog.BlogCategory;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;
import com.knet51.ccweb.jpa.repository.BlogPostRepository;

@Service("repoBlogService")
@Transactional
public class BlogServiceRepositoyImpl implements BlogService {

	@Autowired
	private BlogPostRepository blogPostRepository;
	@Autowired
	private BlogCategoryDao blogCategoryDao;
	
	@Override
	public BlogPost findOne(Long id) {
		return blogPostRepository.findOne(id);
	}

	@Override
	public List<BlogPost> findAllBlogs() {
		@SuppressWarnings("unchecked")
		ArrayList<BlogPost> list = convertToList(blogPostRepository.findAll());
		return list;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private ArrayList convertToList(Iterable iterable) {
		ArrayList list = new ArrayList();
		Iterator itr = iterable.iterator();
		while(itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}

	@Override
	public BlogPost createBlogPost(BlogPost blogPost) {
		return blogPostRepository.save(blogPost);
	}

	@Override
	public List<BlogCategory> findBlogCategories(Long teacher_id) {
		@SuppressWarnings("unchecked")
		ArrayList<BlogCategory> list = convertToList(blogCategoryDao.findAll());
		return list;
	}

	@Override
	public BlogCategory findBlogCategory(Long category_id) {
		return blogCategoryDao.findOne(category_id);
	}

	@Override
	public List<BlogPost> findBlogPosts(Long teacher_id) {
		@SuppressWarnings("unchecked")
		ArrayList<BlogPost> list = convertToList(blogPostRepository.findAll());
		return list;
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
		return blogCategoryDao.save(blogCategory);
	}

	@Override
	public boolean isBlogCategoryExist(String name, Long teacher_id) {
		return (blogCategoryDao.findByNameAndTeacherId(name, teacher_id) != null );
	}

	@Override
	public void deleteBlogCategory(Long blog_category_id) {
		blogCategoryDao.delete(blog_category_id);
	}

	@Override
	public BlogCategory renameBlogCategory(BlogCategory blogCategory) {
		return blogCategoryDao.save(blogCategory);
	}

}
