package com.knet51.ccweb.jpa.services.resources;

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
import com.knet51.ccweb.jpa.repository.course.CourseResourceRepository;

@Transactional
@Service("resourceService")
public class ResourceServiceImpl implements ResourceService {
	
	@Autowired
	private ResourceDao resourceDao;
	
	@Autowired
	private ResourceRepository resourceRepository;
	
	@Autowired
	private CourseResourceRepository courseResourceRepository;


	
	@Override
	public void delete(CourseResource resource, Integer status) {
		resource.setStatus(status);
		courseResourceRepository.save(resource);
		
	}
	
	@Override
	public List<CourseResource> listAllByUser(User user) {
		return courseResourceRepository.findResourceByUserAndStatusAndForbiddenIsNull(user, GlobalDefs.STATUS_RESOURCE);
	}


	@Override
	public CourseResource findOneById(Long id) {
		return courseResourceRepository.findOne(id);
	}

	@Override
	public Page<CourseResource> findAllResouByUserAndStatus(int pageNumber, int pageSize, User user,Integer status) {
		Pageable dateDesc = new PageRequest(pageNumber, pageSize, Direction.DESC, "id"); 
		Page<CourseResource> onePage = courseResourceRepository.findResourceByUserAndStatusAndForbiddenIsNull(user, status, dateDesc);
		return onePage;
	}

	@Override
	public List<CourseResource> findAllByUserAndStatusForSuperAdmin(User user,
			Integer status) {
		return courseResourceRepository.findResourceByUserAndStatus(user, status);
	}

	@Override
	public Page<CourseResource> findAllByUserAndStatusForSuperAdmin(
			int pageNum, int pageSize, User user, Integer status) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<CourseResource> onePage = courseResourceRepository.findResourceByUserAndStatus(user, status, dateDesc);
		return onePage;
	}

	@Override
	public CourseResource updateResource(CourseResource resource) {
		
		return courseResourceRepository.saveAndFlush(resource);
	}

	@Override
	public List<CourseResource> findAllByStatusForSuperAdmin() {
		return courseResourceRepository.findResourceByStatus(GlobalDefs.STATUS_RESOURCE);
	}

	@Override
	public Page<CourseResource> findAllByStatusForSuperAdmin(int pageNum,int pageSize) {
		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
		Page<CourseResource> onePage = courseResourceRepository.findResourceByStatus(GlobalDefs.STATUS_RESOURCE, dateDesc);
		return onePage;
	}

	@Override
	public List<CourseResource> findAllResouByUserAndStatus(User user,
			Integer status) {
		
		return courseResourceRepository.findResourceByUserAndStatus(user, status);
	}

}
