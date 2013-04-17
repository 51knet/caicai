package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseType;


public interface CourseTypeService {
	
	List<CourseType> findAll();
	void destryCourseType(Long id);
	CourseType createCourseType(CourseType courseType);
	CourseType updateCourseType(CourseType courseType);
	CourseType findOneById(Long id);
	
}
