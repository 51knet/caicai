package com.graphene.web.jpa.repository.workexp;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.graphene.web.jpa.entity.resume.WorkExp;

public class WorkExpRepositoryImpl implements WorkExpRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	@Override
	public List<WorkExp> findWorkExpList(Long teacher_id) {
		@SuppressWarnings("unchecked")
		List<WorkExp> workList = em.createQuery("from WorkExp where teacherid="+teacher_id).getResultList();
		return workList;
	}

}
