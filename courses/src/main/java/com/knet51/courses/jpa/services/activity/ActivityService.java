package com.knet51.courses.jpa.services.activity;

import java.util.List;

import org.springframework.data.domain.Page;

import com.knet51.ccweb.jpa.entities.Activity;

public interface ActivityService {
	Page<Activity> findAllPage(int pageNumber,int pageSize);
	List<Activity> findAllList();
	Activity findOne(Long id);
}
