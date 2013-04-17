package com.knet51.ccweb.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.courses.CourseLesson;

public class CourseLessonRepositoryImpl implements CourseLessonRepositoryCustom {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<CourseLesson> getMaxLessonNumByCourseId(Long course_id) {
		List<CourseLesson> lessonList = em.createQuery("from CourseLesson c where c.courseId = "+course_id+" " +
				"and c.lessonNum = (select max(cl.lessonNum) from CourseLesson cl where cl.courseId="+course_id+" )").getResultList();
		return lessonList;
	}

}
