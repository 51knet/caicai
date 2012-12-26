package com.knet51.ccweb.jpa.repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
public class CommentRepositoryImpl implements
		CommentRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Double getMark(Long teacherCourseId) {
		Double mark = (Double) em.createQuery("select avg(mark) from Comment t where t.teachercourseid="+teacherCourseId).getSingleResult();
		return mark;
	}
}
