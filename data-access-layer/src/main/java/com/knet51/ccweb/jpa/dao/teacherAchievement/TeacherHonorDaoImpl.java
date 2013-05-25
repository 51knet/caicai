package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.teacher.TeacherHonor;
@Repository("teacherHonorDao")

public class TeacherHonorDaoImpl implements TeacherHonorDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherHonor save(TeacherHonor teacherHonor) {
		em.persist(teacherHonor);
		return teacherHonor;
	}

	@Override
	public TeacherHonor update(TeacherHonor teacherHonor) {
		em.merge(teacherHonor);
		return teacherHonor;
	}

	@Override
	public TeacherHonor findOneById(Long id) {
		TeacherHonor teacherHonor = em.find(TeacherHonor.class, id);
		return teacherHonor;
	}

	@Override
	public void deleteById(Long id) {
		TeacherHonor teacherHonor = em.find(TeacherHonor.class, id);
		em.remove(teacherHonor);
	}

	@Override
	public List<TeacherHonor> getAllHonorById(Long Id) {
		@SuppressWarnings("unchecked")
		List<TeacherHonor> list = em.createQuery("from TeacherHonor where user_id="+Id).getResultList();
		return list;
	}


}
