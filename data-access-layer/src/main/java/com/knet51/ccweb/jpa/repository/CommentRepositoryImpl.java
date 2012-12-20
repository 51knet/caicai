package com.knet51.ccweb.jpa.repository;

import java.util.ArrayList;
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
		Long mark = (Long) em.createQuery("select sum(mark) from Comment t where t.teachercourseid="+teacherCourseId).getSingleResult();
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
		String hql="select t from Comment t where t.teachercourseid="+teacherCourseId;
		List<Comment> list =(List<Comment>) em.createQuery(hql).getResultList();
		Long personNum=(long) list.size();
		return personNum;
	}

	@Override
	public Comment getComment(Long teacherCourseId, Long userId) {
		Comment comment= new Comment();
		try {
			comment= (Comment) em.createQuery("select t from Comment t where t.teachercourseid="+teacherCourseId +" and t.userid="+userId).getSingleResult();
			if(comment==null){
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return comment;
	}

	@Override
	@SuppressWarnings("unchecked")
	public int getCommentByTeacherCourseIdAndUserId(Long teacherCourseId,
			Long userId) {
		int num=1;
		List<Comment> list=em.createQuery("select t from Comment t where t.teachercourseid="+teacherCourseId +" and t.userid="+userId).getResultList();
		if(list.size()==0){
			num=0;
			return num;
		}
		return num;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<Comment> getAllCourse(Long teacherCourseId) {
		List<Comment> list= new ArrayList<Comment>();
		try {
			list=em.createQuery("select t from Comment t where t.teachercourseid="+teacherCourseId ).getResultList();
			if(list.size()==0){
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


}
