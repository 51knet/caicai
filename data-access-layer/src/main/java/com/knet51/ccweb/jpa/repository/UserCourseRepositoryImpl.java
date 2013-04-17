package com.knet51.ccweb.jpa.repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.courses.UserCourse;
public class UserCourseRepositoryImpl implements
		UserCourseRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Double getMark(Long teacherCourseId) {
		Double mark = (Double) em.createQuery("select avg(mark) from UserCourse t where t.teachercourseid="+teacherCourseId).getSingleResult();
		return mark;
	}

}
