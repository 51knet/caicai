package com.knet51.ccweb.jpa.repository;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;

public interface TeacherCourseResourceRepositoryCustom {
	
	List<CourseResource> getResourceByCourseId(Long course_id);
	List<CourseResource> getResourceByLessonNumAndCourseId(String lessonNum,Long course_id);
	String getMaxLessonNumByCourseId(Long course_id);
	List<CourseResource> findNullResourceByCourseIdAndLessonNum(Long course_id,String lessonNum);
}
