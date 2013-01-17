package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface TeacherCourseResourceRepositoryCustom {
	
	List<CourseResource> getResourceByCourseId(Long course_id);
	CourseResource getResourceByResourceOrderAndCourseId(String resourceOrder,Long course_id);
	String getMaxCourseOrderByCourseId(Long course_id);
}
