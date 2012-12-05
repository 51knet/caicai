package com.knet51.ccweb.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class BlogPostRepositoryImpl implements BlogPostRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

}
