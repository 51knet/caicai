package com.knet51.courses.jpa.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;
import com.knet51.ccweb.jpa.entities.courses.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseResourceRepository;
import com.knet51.courses.controllers.defs.GlobalDefs;
@Transactional
@Service("courseService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private TeacherCourseResourceRepository teacherCourseResourceRepository;

	@Override
	public List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status) {
		List<CourseResource> list= new ArrayList<CourseResource>();
			 try {
				 list=teacherCourseResourceRepository.getResourceByCourseIdAndStatus(course_id,GlobalDefs.STATUS_COURSE_RESOURCE);
			 } catch (Exception e) {
				 e.printStackTrace();
			 } 
			 return list;
		 }
	@Override
	public CourseResource findById(Long id) {
		CourseResource courseResource=teacherCourseResourceRepository.findOne(id);
		return courseResource;
	}
	@Override
	public List<CourseResource> getResourceByLessonNumAndCourseId(
			String LessonNum, Long course_id) {
		List<CourseResource> courseResourceList= new ArrayList<CourseResource>(); 
		try {
			courseResourceList=teacherCourseResourceRepository.getResourceByLessonNumAndCourseId(LessonNum,course_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courseResourceList;
	}


	
}
