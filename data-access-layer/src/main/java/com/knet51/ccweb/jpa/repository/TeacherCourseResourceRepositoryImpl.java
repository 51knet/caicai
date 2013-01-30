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
	public List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status) {
		List<CourseResource> list = em.createQuery("from CourseResource where course_id="+course_id+"and status="+status).getResultList();
		return list;
	}
	@Override
	public String getMaxLessonNumByCourseId(Long course_id) {
		String resourceOrder = (String) em.createQuery("select max(lessonNum) from CourseResource where course_id="+course_id).getSingleResult();
		return resourceOrder;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<CourseResource> getResourceByLessonNumAndCourseId(String lessonNum,
			Long course_id) {
		List<CourseResource> courseResourceList =  em.createQuery("from CourseResource where lessonNum in("+lessonNum+") and course_id="+course_id).getResultList();
		return courseResourceList;
	}


}
