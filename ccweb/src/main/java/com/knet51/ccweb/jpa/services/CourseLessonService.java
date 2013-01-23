package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseLesson;

public interface CourseLessonService {
	
	CourseLesson getMaxLessonNumByCourseId(Long course_id);
	CourseLesson findOne(Long id);
	CourseLesson createCourseLesson(CourseLesson courseLesson);
	CourseLesson destory(Long id);
	List<CourseLesson> findCourseLessonByCourseId(Long course_id);
}
