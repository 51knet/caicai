package com.knet51.ccweb.jpa.repository;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.knet51.ccweb.jpa.entities.Enterprise;

public class EnterpriseRepositoryImpl implements EnterpriseRepositoryCustom{
	@PersistenceContext
	private EntityManager em;

	@Override
	@SuppressWarnings("unchecked")
	public List<Enterprise> findByIsEnterprise() {
		List<Enterprise> list =  em.createQuery("select t from Enterprise t where t.isEnterprise is null  OR t.isEnterprise=''").getResultList();
		return list;
	}
}
