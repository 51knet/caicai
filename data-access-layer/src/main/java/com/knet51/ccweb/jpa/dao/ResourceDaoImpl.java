package com.knet51.ccweb.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.knet51.ccweb.jpa.entities.resource.Resource;

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
	public List<Resource> listAllByUid(Long uId) {
		List<Resource> list = em.createQuery("from Resource where status=1 and user_id="+uId, Resource.class).getResultList();
		return list;
	}

	@Override
	public void delete(Resource resource) {
		em.merge(resource);
	}

	@Override
	public Resource getOneById(Long Id) {
		Resource resource = em.find(Resource.class, Id);
		return resource;
	}

	@Override
	public void deleteResource(Long resource_id) {
		Resource resource = em.find(Resource.class, resource_id);
		em.remove(resource);
	}

}
