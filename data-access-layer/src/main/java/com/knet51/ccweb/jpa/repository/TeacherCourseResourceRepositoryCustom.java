package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;

public interface TeacherCourseResourceRepositoryCustom {
	
	List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status);
	List<CourseResource> getResourceByLessonNumAndCourseId(String lessonNum,Long course_id);
	String getMaxLessonNumByCourseId(Long course_id);
	
}
