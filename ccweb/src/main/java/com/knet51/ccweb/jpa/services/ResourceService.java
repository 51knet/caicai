package com.knet51.ccweb.jpa.services;

import java.util.List;
import org.springframework.data.domain.Page;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;

public interface ResourceService {
	

	void delete(CourseResource resource,Integer status);
	
	List<CourseResource> listAllByUser(User user);
	
	CourseResource findOneById(Long id);
	
	CourseResource updateResource(CourseResource resource);
	
	Page<CourseResource> findAllResouByUserAndStatus(int pageNum, int pageSize, User user,Integer status);
	List<CourseResource> findAllResouByUserAndStatus(User user, Integer status);
	
	/* super admin */
	List<CourseResource> findAllByUserAndStatusForSuperAdmin(User user, Integer status);
	Page<CourseResource> findAllByUserAndStatusForSuperAdmin(int pageNum, int pageSize, User user,Integer status);
	List<CourseResource> findAllByStatusForSuperAdmin();
	Page<CourseResource> findAllByStatusForSuperAdmin(int pageNum, int pageSize);
}
