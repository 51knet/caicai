package com.knet51.ccweb.jpa.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;

@Repository("resourceTypeDao")
public class ResourceTypeDaoImpl implements ResourceTypeDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	public ResourceType save(ResourceType resourceType) {
		em.persist(resourceType);
		return resourceType;
	}

	@Override
	public ResourceType update(ResourceType resourceType) {
		em.merge(resourceType);
		return resourceType;
	}

	@Override
	public ResourceType findOneById(Long id) {
		ResourceType resourceType = em.find(ResourceType.class, id);
		return resourceType;
	}

	@Override
	public void deleteById(Long id) {
		ResourceType resourceType = em.find(ResourceType.class, id);
		em.remove(resourceType);
	}

	@Override
	public List<ResourceType> getAllType() {
		List<ResourceType> list = em.createQuery("from ResourceType", ResourceType.class).getResultList();
		return list;
	}

}
