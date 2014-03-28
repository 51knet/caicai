package com.knet51.courses.jpa.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.repository.course.CourseResourceRepository;
import com.knet51.courses.controllers.defs.GlobalDefs;
@Transactional
@Service("resourcesService")
public class ResourceServiceImpl implements ResourceService {

//	@Autowired
//	private CourseResourceRepository courseResourceRepository;
//
//	@Override
//	public List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status) {
//		List<CourseResource> list= new ArrayList<CourseResource>();
//			 try {
//				 list=courseResourceRepository.getResourceByCourseIdAndStatus(course_id,GlobalDefs.STATUS_COURSE_RESOURCE);
//			 } catch (Exception e) {
//				 e.printStackTrace();
//			 } 
//			 return list;
//		 }
//	@Override
//	public CourseResource findById(Long id) {
//		CourseResource courseResource=courseResourceRepository.findOne(id);
//		return courseResource;
//	}
//	@Override
//	public List<CourseResource> getResourceByLessonNumAndCourseId(
//			int LessonNum, Long course_id) {
//		List<CourseResource> courseResourceList= new ArrayList<CourseResource>(); 
//		try {
//			courseResourceList=courseResourceRepository.getResourceByLessonNumAndCourseId(LessonNum,course_id);
//		} catch (Exception e) {
//			e.printStackTrace();
//		} 
//		return courseResourceList;
//	}


	
}
