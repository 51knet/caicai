package com.knet51.ccweb.jpa.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.blog.BlogCategory;

@Repository
public class BlogCategoryDaoImpl extends CrudImpl<BlogCategory, Long> implements
		BlogCategoryDao {

	public BlogCategoryDaoImpl() {
		super(BlogCategory.class);
	}

	public BlogCategoryDaoImpl(Class<BlogCategory> domainClass) {
		super(domainClass);
	}

	@Override
	public BlogCategory findByNameAndTeacherId(String name, Long teacher_id) {
		// TDODO: fix the logic
		TypedQuery<BlogCategory> query = getEntityManager()
				.createQuery(
						"SELECT DISTINCT c FROM Teacher t, BlogCategory c WHERE c.name=:name",
						BlogCategory.class);
		query.setParameter("name", name);
		return query.getSingleResult();
	}
}
