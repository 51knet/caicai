package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.ResourceDao;
import com.knet51.ccweb.jpa.entities.Teacher;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.repository.ResourceRepository;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ResourceRepository resourceRepository;

	@Override
	public Resource create(Resource resource,User user) {
		resource.setUser(user);
		return resourceDao.save(resource);
	}

	@Override
	public List<Resource> listAllByUid(Long uId) {
		return resourceDao.listAllByUid(uId);
	}

	@Override
	public void delete(Resource resource, Integer status) {
		resource.setStatus(status);
		resourceDao.delete(resource);
		
	}

	@Override
	public Resource findOneById(Long Id) {
		return resourceDao.getOneById(Id);
	}

	@Override
	public Page<Resource> findAllResouByUser(int pageNumber, int pageSize, User user) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<Resource> onePage = resourceRepository.findResourceByUser(user, dateDesc);
		return onePage;
	}

	@Override
	public void deleteResource(Long resource_id) {
		resourceDao.deleteResource(resource_id);
	}

}
