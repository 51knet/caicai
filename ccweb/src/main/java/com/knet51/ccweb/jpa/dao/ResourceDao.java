package com.knet51.ccweb.jpa.dao;

import java.util.List;


import com.knet51.ccweb.jpa.entities.resource.Resource;

public interface ResourceDao {
	
	Resource save(Resource resource);
	
	void delete(Resource resource);
	
	List<Resource> listAllByUid(Long uId);
	
	Resource getOneById(Long Id);
}
