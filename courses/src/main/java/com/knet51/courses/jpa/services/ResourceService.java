package com.knet51.courses.jpa.services;

import java.util.List;

import com.knet51.ccweb.jpa.entities.teacher.CourseResource;
import com.knet51.ccweb.jpa.entities.teacher.TeacherCourse;

public interface ResourceService {
	List<CourseResource> getResourceByCourseIdAndStatus(Long course_id,Integer status);
	List<CourseResource> getResourceByLessonNumAndCourseId(String LessonNum,Long course_id);
	CourseResource findById(Long id);
}
