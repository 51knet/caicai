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
	@Override
	public String getMaxCourseOrderByCourseId(Long course_id) {
		String resourceOrder = (String) em.createQuery("select max(resourceOrder) from CourseResource where course_id="+course_id).getSingleResult();
		return resourceOrder;
	}

	@Override
	@SuppressWarnings("unused")
	public CourseResource getResourceByResourceOrderAndCourseId(String resourceOrder,
			Long course_id) {
		CourseResource courseResource = (CourseResource) em.createQuery("from CourseResource where resourceOrder="+resourceOrder+" and course_id="+course_id).getSingleResult();
		return courseResource;
	}

}
