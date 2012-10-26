package com.knet51.ccweb.jpa.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public class TeacherCourseRepositoryImpl implements
		TeacherCourseRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherCourse updateTeacherCourseDetail(TeacherCourse teacherCourse) {
		em.merge(teacherCourse);
		return teacherCourse;
	}

}
