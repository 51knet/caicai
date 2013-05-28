package com.knet51.ccweb.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.courses.CourseLesson;

public interface CourseLessonService {
	
	List<CourseLesson> getMaxLessonNumByCourseId(Long course_id);
	CourseLesson findOne(Long id);
	CourseLesson createCourseLesson(CourseLesson courseLesson);
	void destory(Long id);
	List<CourseLesson> findCourseLessonByCourseId(Long course_id);
	List<CourseLesson> findCourseLessonByCourseIdAndLessonNum(Long course_id,int lessonNum);
}
