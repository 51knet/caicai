package com.knet51.ccweb.jpa.repository.course;

import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseResource;

public interface CourseResourceRepositoryCustom {
	
	List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status);
	List<CourseResource> getResourceByLessonNumAndCourseId(int lessonNum,Long course_id);
	int getMaxLessonNumByCourseId(Long course_id);
	
}
