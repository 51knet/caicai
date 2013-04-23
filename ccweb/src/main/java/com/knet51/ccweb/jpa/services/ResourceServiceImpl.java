package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.controllers.common.defs.GlobalDefs;
import com.knet51.ccweb.jpa.dao.ResourceDao;
import com.knet51.ccweb.jpa.entities.User;
import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.repository.ResourceRepository;
import com.knet51.ccweb.jpa.repository.TeacherCourseResourceRepository;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private TeacherCourseResourceRepository courseResourceRepository;


	
	@Override
	public void delete(CourseResource resource, Integer status) {
		resource.setStatus(status);
		courseResourceRepository.save(resource);
		
	}
	
	@Override
	public List<CourseResource> listAllByUser(User user) {
		return courseResourceRepository.findResourceByUserAndStatus(user, GlobalDefs.STATUS_RESOURCE);
	}


	@Override
	public CourseResource findOneById(Long id) {
		return courseResourceRepository.findOne(id);
	}

	@Override
	public Page<CourseResource> findAllResouByUserAndStatus(int pageNumber, int pageSize, User user,Integer status) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<CourseResource> onePage = courseResourceRepository.findResourceByUserAndStatus(user, status, dateDesc);
		return onePage;
	}

}
