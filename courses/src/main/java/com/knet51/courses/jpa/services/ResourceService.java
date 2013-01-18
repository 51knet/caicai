package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface ResourceService {
	List<CourseResource> getResourceByCourseId(Long course_id);
	CourseResource getResourceByCourseOrderAndCourseId(String resourceOrder,Long course_id);
	CourseResource findById(Long id);
}
