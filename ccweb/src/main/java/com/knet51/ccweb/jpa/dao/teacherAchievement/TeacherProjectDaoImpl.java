package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.teacher.TeacherProject;
@Repository("teacherProjectDao")

public class TeacherProjectDaoImpl implements TeacherProjectDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherProject save(TeacherProject teacherProject) {
		em.persist(teacherProject);
		return teacherProject;
	}

	@Override
	public TeacherProject update(TeacherProject teacherProject) {
		em.merge(teacherProject);
		return teacherProject;
	}

	@Override
	public TeacherProject findOneById(Long id) {
		TeacherProject teacherProject = em.find(TeacherProject.class, id);
		return teacherProject;
	}

	@Override
	public void deleteById(Long id) {
		TeacherProject teacherProject = em.find(TeacherProject.class, id);
		em.remove(teacherProject);
	}

	@Override
	public List<TeacherProject> getAllProjectById(Long Id) {
		@SuppressWarnings("unchecked")
		List<TeacherProject> list = em.createQuery("from TeacherProject where teacher_id="+Id).getResultList();
		return list;
	}


}
