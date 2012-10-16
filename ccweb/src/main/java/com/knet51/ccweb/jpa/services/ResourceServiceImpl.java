package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.dao.ResourceDao;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;

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
		// TODO Auto-generated method stub
		return resourceDao.getOneById(Id);
	}

}
