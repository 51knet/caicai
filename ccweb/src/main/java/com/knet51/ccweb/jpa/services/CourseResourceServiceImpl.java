package com.knet51.ccweb.jpa.services;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.NoResultException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;
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
	public List<CourseResource> getAllCourseResourceByCourseIdAndStatus(Long course_id,Integer status) {
		return resourceRepository.getResourceByCourseIdAndStatus(course_id,status);
	}

	@Override
	public void deleCourseResource(Long resource_id) {
		resourceRepository.delete(resource_id);
	}
	

	@Override
	public String getMaxLessonNumByCourseId(Long course_id) {
		String LessonNum = "";
		try {
			LessonNum = resourceRepository.getMaxLessonNumByCourseId(course_id);
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return LessonNum == null? "0":LessonNum;
	}

	public List<CourseResource> getResourceByLessonNumAndCourseId(
			String lessonNum, Long course_id) {
		List<CourseResource>  courseResourceList= new ArrayList<CourseResource>(); 
		try {
			courseResourceList=resourceRepository.getResourceByLessonNumAndCourseId(lessonNum,course_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courseResourceList;
	}

	

//	@Override
//	public Page<CourseResource> findAllCourseResourceByCourse(int pageNum,
//			int pageSize, TeacherCourse course) {
//		Pageable dateDesc = new PageRequest(pageNum, pageSize, Direction.DESC, "id");
//		Page<CourseResource> onePage = resourceRepository.findTeacherCourseResourceByCourse(course, dateDesc);
//		return onePage;
//	}

}
