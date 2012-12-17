package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface CourseService {
	List<CourseResource> getResourceByCourseId(Long course_id);
	List<CourseResource> getResourceByResourceOrder(String resourceOrder);
}
