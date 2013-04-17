package com.knet51.ccweb.jpa.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.courses.UserCourse;
public class TeacherRepositoryImpl implements TeacherRepositoryCustom{
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Teacher> findByIsEnterprise() {
		List<Teacher> list =  em.createQuery("select t from Teacher t where t.isEnterprise is null  OR t.isEnterprise=''").getResultList();
		return list;
	}
}
