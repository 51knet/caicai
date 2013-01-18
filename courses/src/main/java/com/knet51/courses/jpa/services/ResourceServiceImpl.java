package com.knet51.courses.jpa.services;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;
import com.knet51.ccweb.jpa.repository.TeacherCourseResourceRepository;
@Transactional
@Service("courseService")
public class ResourceServiceImpl implements ResourceService {

	@Autowired
	private TeacherCourseResourceRepository teacherCourseResourceRepository;

	@Override
	public List<CourseResource> getResourceByCourseId(Long course_id) {
		List<CourseResource> list= new ArrayList<CourseResource>();
			 try {
				 list=teacherCourseResourceRepository.getResourceByCourseId(course_id);
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
	public CourseResource getResourceByCourseOrderAndCourseId(
			String resourceOrder, Long course_id) {
		CourseResource courseResource= new CourseResource(); 
		try {
			courseResource=teacherCourseResourceRepository.getResourceByCourseOrderAndCourseId(resourceOrder,course_id);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return courseResource;
	}


	
}
