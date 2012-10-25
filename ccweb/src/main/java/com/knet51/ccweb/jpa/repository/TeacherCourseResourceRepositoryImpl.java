package com.knet51.ccweb.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public class TeacherCourseResourceRepositoryImpl implements TeacherCourseResourceRepositoryCustom {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CourseResource> getResourceByCourseId(Long course_id) {
		List<CourseResource> list = em.createQuery("from CourseResource where course_id="+course_id).getResultList();
		return list;
	}

}
