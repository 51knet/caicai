package com.knet51.ccweb.jpa.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository("baseDao")
public class BaseDaoImpl<T> implements BaseDao<T> {
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public T findOne(Class<T> clazz,Long id) {
		
		return (T) em.find(clazz.getClass(), id );
	}

	@Override
	public void delete(Class<T> clazz,Long id) {
		T entity = em.find(clazz, id);
		em.remove(entity);
		
	}

	@Override
	public T update(T entity) {
		
		return em.merge(entity);
	}

	@Override
	public T create(T entity) {
		System.out.println("------- cerate");
		T newentity = em.merge(entity);
		System.out.println("------- end="+newentity.getClass());
		return entity;
	}

}
