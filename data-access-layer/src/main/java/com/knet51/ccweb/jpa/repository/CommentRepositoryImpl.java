package com.knet51.ccweb.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;


import com.knet51.ccweb.jpa.entities.teacher.Comment;

public class CommentRepositoryImpl implements
		CommentRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public Long getMark(Long teacherCourseId) {
		Long mark = (Long) em.createQuery("select sum(mark) from Comment t where t.courseid="+teacherCourseId).getSingleResult();
		return mark;
	}

	@Override
	public Comment createComment(Comment comment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	public Long getPerson(Long teacherCourseId) {
		String hql="select t from Comment t where t.courseid="+teacherCourseId;
		List<Comment> list =(List<Comment>) em.createQuery(hql).getResultList();
		Long personNum=(long) list.size();
		return personNum;
	}

	@Override
	public Long getMark(Long teacherCourseId, Long userId) {
		Long markNum=(Long) em.createQuery("select t.mark from Comment t where t.courseid="+teacherCourseId +" and t.userid="+userId).getSingleResult();
		return markNum;
	}

}
