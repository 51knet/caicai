package com.knet51.ccweb.jpa.dao;

import java.util.List;


import com.knet51.ccweb.jpa.entities.resource.Resource;

public interface ResourceDao {
	
	Resource save(Resource resource);
	
	void delete(Resource resource);//改变status的值
	
	List<Resource> listAllByUid(Long uId);
	
	Resource getOneById(Long Id);
	
	void deleteResource(Long resource_id);//从数据库中删除
}
