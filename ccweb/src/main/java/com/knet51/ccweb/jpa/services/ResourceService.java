package com.knet51.ccweb.jpa.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.resource.Resource;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface ResourceService {
	

	void delete(CourseResource resource,Integer status);
	
	List<CourseResource> listAllByUser(User user);
	
	CourseResource findOneById(Long id);
	
	Page<CourseResource> findAllResouByUserAndStatus(int pageNum, int pageSize, User user,Integer status);
	
}
