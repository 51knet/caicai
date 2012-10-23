package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;

public interface ResourceService {
	
	Resource create(Resource resource,User user);
	
	void delete(Resource resource,Integer status);
	
	List<Resource> listAllByUid(Long uId);
	
	Resource findOneById(Long Id);
	
	Page<Resource> findAllResouById(int pageNum, int pageSize, User user);
}
