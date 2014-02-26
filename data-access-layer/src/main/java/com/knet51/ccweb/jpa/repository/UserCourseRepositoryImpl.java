package com.knet51.ccweb.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


public class UserCourseRepositoryImpl implements UserCourseRepositoryCustom{
	@PersistenceContext
	private EntityManager em;

	@Override
	public Double getMark(Long teachercourseId) {
		Double mark = (Double) em.createQuery("select avg(mark) from UserCourse t where t.teachercourseid ="+teachercourseId).getSingleResult();
		return mark;
	}
	
	
}
