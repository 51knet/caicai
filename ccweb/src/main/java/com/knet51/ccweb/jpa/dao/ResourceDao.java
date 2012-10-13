package com.knet51.ccweb.jpa.dao;

import java.util.List;


import com.knet51.ccweb.jpa.entities.Resource;

public interface ResourceDao {
	
	Resource save(Resource resource);
	
	void deleteById(Long id);
	
	List<Resource> listAllByUid(Long uId);
}
