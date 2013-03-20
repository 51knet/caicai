package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.ResourceTypeDao;
import com.knet51.ccweb.jpa.entities.resource.ResourceType;
import com.knet51.ccweb.jpa.repository.ResourceTypeRepository;
@Transactional
@Service("resourceTypeService")
public class ResourceTypeServiceImpl implements ResourceTypeService {
	@Autowired
	private ResourceTypeDao resourceTypeDao;
	@Autowired
	private ResourceTypeRepository resourceTypeRepository;

	@Override
	public ResourceType save(ResourceType resourceType) {
		return resourceTypeDao.save(resourceType);
	}

	@Override
	public ResourceType update(ResourceType resourceType) {
		return resourceTypeDao.update(resourceType);
	}

	@Override
	public ResourceType findOneById(Long id) {
		return resourceTypeDao.findOneById(id);
	}

	@Override
	public void deleteById(Long id) {
		resourceTypeDao.deleteById(id);
	}

	@Override
	public List<ResourceType> getAllType() {
		// TODO Auto-generated method stub
		return resourceTypeDao.getAllType();
	}

	@Override
	public List<ResourceType> getTypeByTypeStatus(String typeStatus) {
		return resourceTypeRepository.findResourceTypeByTypeStatus(typeStatus);
	}

}
