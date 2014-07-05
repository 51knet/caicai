package com.graphene.web.jpa.dao.achievement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.graphene.web.jpa.entity.teacher.TeacherPatent;

@Repository("teacherPatentDao")

public class TeacherPatentDaoImpl implements TeacherPatentDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherPatent save(TeacherPatent teacherPatent) {
		em.persist(teacherPatent);
		return teacherPatent;
	}

	@Override
	public TeacherPatent update(TeacherPatent teacherPatent) {
		em.merge(teacherPatent);
		return teacherPatent;
	}

	@Override
	public TeacherPatent findOneById(Long id) {
		TeacherPatent teacherPatent = em.find(TeacherPatent.class, id);
		return teacherPatent;
	}

	@Override
	public void deleteById(Long id) {
		TeacherPatent teacherPatent = em.find(TeacherPatent.class, id);
		em.remove(teacherPatent);
	}

	@Override
	public List<TeacherPatent> getAllPatentById(Long Id) {
		@SuppressWarnings("unchecked")
		List<TeacherPatent> list = em.createQuery("from TeacherPatent where user_id="+Id).getResultList();
		return list;
	}


}
