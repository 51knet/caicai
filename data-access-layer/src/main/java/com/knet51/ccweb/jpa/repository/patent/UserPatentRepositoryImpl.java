package com.knet51.ccweb.jpa.repository.patent;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;



import com.knet51.ccweb.jpa.entities.patent.Patent;

public class UserPatentRepositoryImpl implements UserPatentRepositoryCustom {

	@PersistenceContext
	private EntityManager em;
	

	@Override
	public List<Patent> findPatentPage(Long type_id,String search, String params,int pageNumber,int pageSize) {
		//List<Patent> patentList = em.createQuery().getResultList();
		Query q = em.createQuery("from Patent p where p."+search+" like '%"+params+"%'");
		q.setFirstResult(pageNumber);
		q.setMaxResults(pageSize);
		return q.getResultList();
	}


	@Override
	public List<Patent> findPatentList(Long type_id,String search, String params) {
		Query q = em.createQuery("from Patent p where p."+search+" like '%"+params+"%'");
		return q.getResultList();
	}


	@Override
	public List<Patent> findPatentPageDetail() {
		// TODO Auto-generated method stub
		return null;
	}
}
