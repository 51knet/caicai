package com.knet51.ccweb.jpa.dao;
import java.util.List;

import com.knet51.ccweb.jpa.entities.resource.ResourceType;

public interface ResourceTypeDao {
	ResourceType save(ResourceType resourceType);

	ResourceType update(ResourceType resourceType);

	ResourceType findOneById(Long id);

	void deleteById(Long id);
	
	List<ResourceType> getAllType(); 
}
