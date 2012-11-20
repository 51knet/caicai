package com.knet51.ccweb.jpa.repository;

import java.util.List;

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

	@Override
	public List<TeacherCourse> getAllCourseById(Long id) {
		List<TeacherCourse> courseList = em.createQuery("from TeacherCourse where teacher_id="+id).getResultList();
		return courseList;
	}
	

}
