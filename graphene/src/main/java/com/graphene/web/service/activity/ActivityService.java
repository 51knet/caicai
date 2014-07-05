package com.graphene.web.service.activity;

import java.util.List;

import org.springframework.data.domain.Page;

import com.graphene.web.jpa.entity.Activity;


public interface ActivityService {
	Page<Activity> findAllPage(int pageNumber,int pageSize);
	List<Activity> findAllList();
	Activity findOne(Long id);
	Activity create(Activity activity);
	Activity update(Activity activity);
	void delete(Long id);
	
}
