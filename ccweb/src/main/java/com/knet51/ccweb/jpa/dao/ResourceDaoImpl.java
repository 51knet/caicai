package com.knet51.ccweb.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.Resource;

@Repository("resourceDao")
public class ResourceDaoImpl implements ResourceDao {
	
	@PersistenceContext
	private EntityManager em;
	@Override
	public Resource save(Resource resource) {
		em.persist(resource);
		return resource;
	}

	@Override
	public void deleteById(Long id) {
		Resource resource = em.find(Resource.class, id);
		em.remove(resource);
	}

	@Override
	public List<Resource> listAllByUid(Long uId) {
		List<Resource> list = em.createQuery("from Resource where user_id="+uId, Resource.class).getResultList();
		return list;
	}

}
