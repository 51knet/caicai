package com.knet51.ccweb.jpa.dao.teacherAchievement;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import com.knet51.ccweb.jpa.entities.teacher.TeacherThesis;

@Repository("teacherThesisDao")
public class TeacherThesisDaoImpl implements TeacherThesisDao {
	@PersistenceContext
	private EntityManager em;

	@Override
	public TeacherThesis save(TeacherThesis teacherThesis) {
		em.persist(teacherThesis);
		return teacherThesis;
	}

	@Override
	public TeacherThesis update(TeacherThesis teacherThesis) {
		em.merge(teacherThesis);
		return teacherThesis;
	}

	@Override
	public TeacherThesis findOneById(Long id) {
		TeacherThesis teacherThesis = em.find(TeacherThesis.class, id);
		return teacherThesis;
	}

	@Override
	public void deleteById(Long id) {
		TeacherThesis teacherThesis = em.find(TeacherThesis.class, id);
		em.remove(teacherThesis);
	}

	@Override
	public List<TeacherThesis> getAllThesisById(Long Id) {
		@SuppressWarnings("unchecked")
		List<TeacherThesis> list = em.createQuery("from TeacherThesis where teacher_id="+Id).getResultList();
		return list;
	}

	

}
