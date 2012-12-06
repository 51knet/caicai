package com.knet51.ccweb.jpa.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.EduBackground;

public class EduBackgroundRepositoryImpl implements EduBackgroundRepositoryCustom {
	@PersistenceContext
	private EntityManager em;

	

	@Override
	public EduBackground findOneByTeacherId(Long teacher_id) {
		List<EduBackground> eduList = em.createQuery("from EduBackground where teacherid="+teacher_id).getResultList();
		if(eduList !=null && eduList.size()>0){
			return eduList.get(0);
		}else{
			return null;
		}
		
	}



	@Override
	public List<EduBackground> findEduList(Long teacher_id) {
		List<EduBackground> eduList = em.createQuery("from EduBackground where teacherid="+teacher_id).getResultList();
		return eduList;
	}

}
