package com.knet51.ccweb.jpa.services.resources;

import java.util.List;

import com.knet51.ccweb.jpa.entities.resource.ResourceType;

public interface ResourceTypeService {
	ResourceType save(ResourceType resourceType);

	ResourceType update(ResourceType resourceType);

	ResourceType findOneById(Long id);

	void deleteById(Long id);
	
	List<ResourceType> getAllType(); 
	
	List<ResourceType> getTypeByTypeStatus(String typeStatus);
}
