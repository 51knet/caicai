package com.knet51.ccweb.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.UserCourse;

public class UserCourseRepositoryImpl implements UserCourseRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<UserCourse> findCourseByUserId(Long user_id) {
		List<UserCourse> userCourse = em.createQuery("from UserCourse where user_id="+user_id).getResultList();
		return userCourse;
	}

}
