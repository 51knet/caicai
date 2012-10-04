package com.knet51.ccweb.jpa.services;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.BlogDao;
import com.knet51.ccweb.jpa.entities.blog.BlogPost;

@Service
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao blogDao;
	
	@Override
	public BlogPost findOne(Long id) {
		return blogDao.findOne(id);
	}

	@Override
	public List<BlogPost> findAllBlogs() {
		// TODO Auto-generated method stub
		ArrayList<BlogPost> list = convertToList(blogDao.findAll());
		return list;
	}

	private ArrayList<BlogPost> convertToList(Iterable<BlogPost> iterable) {
		ArrayList<BlogPost> list = new ArrayList<BlogPost>();
		Iterator<BlogPost> itr = iterable.iterator();
		while(itr.hasNext()) {
			list.add(itr.next());
		}
		return list;
	}

}
