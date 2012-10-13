package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.Resource;
import com.knet51.ccweb.jpa.entities.User;

public interface ResourceService {
	
	Resource create(Resource resource,User user);
	
	void deleteById(Long id);
	
	List<Resource> listAllByUid(Long uId);
}
