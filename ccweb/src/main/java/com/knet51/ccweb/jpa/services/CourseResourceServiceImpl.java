package com.knet51.ccweb.jpa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseResourceRepository;

@Transactional
@Service("courseResourceService")
public class CourseResourceServiceImpl implements CourseResourceService {
	
	@Autowired
	private TeacherCourseResourceRepository resourceRepository;
	
	@Override
	public CourseResource findOneById(Long resource_id) {
		return resourceRepository.findOne(resource_id);
	}

	@Override
	public CourseResource createCourseResource(CourseResource courseResource) {
		return resourceRepository.save(courseResource);
	}

	@Override
	public CourseResource updateCourseResource(CourseResource CourseResource) {
		return null;
	}

	@Override
	public List<CourseResource> getAllCourseResourceById(Long course_id) {
		return resourceRepository.getResourceByCourseId(course_id);
	}

	@Override
	public void deleCourseResource(Long course_id) {

	}

//	@Override
//	public Page<CourseResource> findAllCourseResourceByCourse(int pageNum,
//			int pageSize, TeacherCourse course) {
//		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
//		Page<CourseResource> onePage = resourceRepository.findTeacherCourseResourceByCourse(course, dateDesc);
//		return onePage;
//	}

}
