package com.knet51.courses.jpa.services.activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.knet51.ccweb.jpa.entities.Activity;
import com.knet51.ccweb.jpa.repository.ActivityRepository;
@Service("activityService")
public class ActivityServiceImpl implements ActivityService {
	
	@Autowired
	private ActivityRepository activityRepository;
	
	@Override
	public Page<Activity> findAllPage(int pageNumber, int pageSize) {
		Pageable pageable = new PageRequest(pageNumber, pageSize, Direction.DESC, "id");
		Page<Activity> page = activityRepository.findAll(pageable);
		
		return page;
	}

	@Override
	public List<Activity> findAllList() {
		List<Activity> list = activityRepository.findAll();
		return list;
	}

	@Override
	public Activity findOne(Long id) {
		return activityRepository.findOne(id);
	}

}
