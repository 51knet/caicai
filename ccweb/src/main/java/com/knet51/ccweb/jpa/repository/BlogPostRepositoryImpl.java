package com.knet51.ccweb.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class BlogPostRepositoryImpl implements BlogPostRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;

}
